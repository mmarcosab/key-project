package br.com.itau.pix.keyprocessor.domain.usecase.impl;

import br.com.itau.pix.keyprocessor.domain.AccountValidator;
import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.KeyValidator;
import br.com.itau.pix.keyprocessor.domain.exception.ExceededQuantityException;
import br.com.itau.pix.keyprocessor.domain.exception.KeyAlreadyExistsException;
import br.com.itau.pix.keyprocessor.domain.exception.KeyAlreadyInactiveException;
import br.com.itau.pix.keyprocessor.domain.exception.KeyNotFoundException;
import br.com.itau.pix.keyprocessor.domain.factory.KeyPixFactory;
import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.KeyPixService;
import br.com.itau.pix.keyprocessor.infra.rest.factory.GetKeyResponseFactory;
import br.com.itau.pix.keyprocessor.infra.rest.factory.InactivateKeyResponseFactory;
import br.com.itau.pix.keyprocessor.infra.rest.factory.UpdateKeyResponseFactory;
import br.com.itau.pix.keyprocessor.infra.rest.in.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.in.KeyFilterParam;
import br.com.itau.pix.keyprocessor.infra.rest.in.UpdateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.out.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class KeyPixServiceImpl implements KeyPixService {

    private final KeyRepository keyRepository;
    private final KeyPixPresenter keyPixPresenter;
    private final AccountTypeRepository accountTypeRepository;

    private final Logger logger = Logger.getLogger(KeyPixServiceImpl.class.getName());

    public KeyPixServiceImpl(
            final KeyRepository keyRepository,
            final KeyPixPresenter keyPixPresenter,
            final AccountTypeRepository accountTypeRepository) {
        this.keyRepository = keyRepository;
        this.keyPixPresenter = keyPixPresenter;
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public CreateKeyResponse create(CreateKeyRequest createKeyRequest) {
        try {
            var keyPix = make(createKeyRequest);
            KeyValidator.isValid(keyPix.getType(), keyPix.getValue());
            AccountValidator.isValid(keyPix.getAccountType(), keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            if (keyRepository.existsByValue(keyPix.getValue())) {
                throw new KeyAlreadyExistsException();
            }
            final String typeAccount = accountTypeRepository.verifyTypeAccount(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            if ("PF".equals(typeAccount)) {
                verifyQuantityPF(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            } else {
                verifyQuantityPJ(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            }
            final String id = keyRepository.save(keyPix);
            return keyPixPresenter.prepareSuccessView(new CreateKeyResponse(id));

        } catch(KeyAlreadyExistsException ke){
            return keyPixPresenter.prepareCreateKeyResponseFailView(ke.getMessage());
        } catch(Exception e) {
            return keyPixPresenter.prepareCreateKeyResponseFailView(e.getMessage());
        }
    }

    public GetKeyResponse getById(final String id) {
        try {
            var optionalKey = keyRepository.findById(id);
            if (optionalKey.isEmpty()) {
                throw new KeyNotFoundException();
            }
            var keyPix = optionalKey.get();
            return keyPixPresenter.prepareSuccessView(
                    GetKeyResponseFactory.create(
                            keyPix.getId(),
                            keyPix.getName(),
                            keyPix.getType(),
                            keyPix.getValue(),
                            keyPix.getAccountType(),
                            keyPix.getAgencyNumber(),
                            keyPix.getAccountNumber(),
                            keyPix.getAccountHolderName(),
                            keyPix.getAccountHolderLastName(),
                            keyPix.getDateTimeInclusion(),
                            keyPix.getDateTimeUpdate()
                    )
            );
        } catch(KeyNotFoundException k) {
            return keyPixPresenter.prepareGetNotFoundKeyResponseFailView();
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareGetKeyResponseFailView(e.getMessage());
        }

    }
    @Override
    public List<GetKeyResponse> getByFilter(KeyFilterParam keyFilterParam) {
        try {
            var keyData = keyRepository.findByCombinedFilter(
                    keyFilterParam.getType(),
                    keyFilterParam.getAccountHolderName(),
                    keyFilterParam.getAccountHolderLastName(),
                    keyFilterParam.getAgencyNumber(),
                    keyFilterParam.getAccountNumber()
            );
            if(keyData.isEmpty()) {
                throw new KeyNotFoundException();
            }
            return keyPixPresenter.prepareSuccessView(keyData.stream().map(key -> convert(key)).collect(Collectors.toList()));
        } catch(KeyNotFoundException k) {
            return keyPixPresenter.prepareListGetNotFoundKeyResponseFailView();
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareListGetKeyResponseFailView(e.getMessage());
        }
    }

    public GetKeyResponse getByValue(final String value) {
        try {
            var optionalKey = keyRepository.findByValue(value);
            if(optionalKey.isEmpty()) {
                throw new KeyNotFoundException();
            }
            var keyPix = optionalKey.get();
            return keyPixPresenter.prepareSuccessView(
                    GetKeyResponseFactory.create(
                            keyPix.getId(),
                            keyPix.getName(),
                            keyPix.getType(),
                            keyPix.getValue(),
                            keyPix.getAccountType(),
                            keyPix.getAgencyNumber(),
                            keyPix.getAccountNumber(),
                            keyPix.getAccountHolderName(),
                            keyPix.getAccountHolderLastName(),
                            keyPix.getDateTimeInclusion(),
                            keyPix.getDateTimeUpdate()
                    )
            );
        } catch(KeyNotFoundException k) {
            return keyPixPresenter.prepareGetNotFoundKeyResponseFailView();
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareGetKeyResponseFailView(e.getMessage());
        }

    }

    public InactivateKeyResponse inactivateById(final String id) {
        try {
            var optionalKeyPix = keyRepository.findById(id);
            if (optionalKeyPix.isEmpty()) {
                throw new KeyNotFoundException();
            }
            var keyPix = optionalKeyPix.get();
            if (!keyPix.isActive()) {
                throw new KeyAlreadyInactiveException();
            }
            keyPix.inactivate();
            keyRepository.inactivateById(keyPix);
            return keyPixPresenter.prepareSuccessView(
                    InactivateKeyResponseFactory.create(
                            keyPix.getId(),
                            keyPix.getName(),
                            keyPix.getType(),
                            keyPix.getValue(),
                            keyPix.getAccountType(),
                            keyPix.getAgencyNumber(),
                            keyPix.getAccountNumber(),
                            keyPix.getAccountHolderName(),
                            keyPix.getAccountHolderLastName(),
                            keyPix.getDateTimeInclusion(),
                            LocalDateTime.now(),
                            keyPix.isActive()
                    )
            );
        } catch(KeyNotFoundException knf) {
            return keyPixPresenter.prepareInactivateNotFoundKeyResponseFailView();
        } catch(KeyAlreadyInactiveException kai) {
            return keyPixPresenter.prepareInactiveKeyResponseFailView(kai.getMessage());
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareInactiveKeyResponseFailView(e.getMessage());
        }
    }

    public UpdateKeyResponse update(final UpdateKeyRequest updateKeyRequest, final String id) {
        try {
            var optionalKeyPix = keyRepository.findById(id);
            if (optionalKeyPix.isEmpty()) {
                throw new KeyNotFoundException();
            }
            var keyPix = optionalKeyPix.get();
            if (!keyPix.isActive()) {
                throw new KeyAlreadyInactiveException();
            }
            var keyPixUpdated = createDomain(updateKeyRequest, keyPix);
            AccountValidator.isValid(keyPix.getAccountType(), keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            keyRepository.save(keyPixUpdated);
            return keyPixPresenter.prepareSuccessView(createResponse(keyPixUpdated));
        } catch(KeyNotFoundException k) {
            return keyPixPresenter.prepareUpdateKeyNotFoundKeyResponseFailView();
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareUpdateKeyResponseFailView(e.getMessage());
        }

    }

    private KeyPix createDomain(final UpdateKeyRequest updateKeyRequest, final KeyPix keyPix){
        return KeyPixFactory.create(
                keyPix.getId(),
                updateKeyRequest.getName() == null? keyPix.getName() : updateKeyRequest.getName(),
                keyPix.getType(),
                keyPix.getValue(),
                updateKeyRequest.getAccountType() == null? keyPix.getAccountType() : updateKeyRequest.getAccountType(),
                updateKeyRequest.getAgencyNumber() == null? keyPix.getAgencyNumber() : updateKeyRequest.getAgencyNumber(),
                updateKeyRequest.getAccountNumber() == null? keyPix.getAccountNumber() : updateKeyRequest.getAccountNumber(),
                updateKeyRequest.getAccountHolderName() == null? keyPix.getAccountHolderName() : updateKeyRequest.getAccountHolderName(),
                updateKeyRequest.getAccountHolderLastName() == null? keyPix.getAccountHolderLastName() : updateKeyRequest.getAccountHolderLastName(),
                keyPix.getDateTimeInclusion(),
                LocalDateTime.now(),
                keyPix.isActive()? 1 : 0
        );
    }

    private UpdateKeyResponse createResponse(final KeyPix keyPix) {
        return UpdateKeyResponseFactory.create(
                keyPix.getId(),
                keyPix.getName(),
                keyPix.getType(),
                keyPix.getValue(),
                keyPix.getAccountType(),
                keyPix.getAgencyNumber(),
                keyPix.getAccountNumber(),
                keyPix.getAccountHolderName(),
                keyPix.getAccountHolderLastName(),
                keyPix.getDateTimeInclusion(),
                keyPix.getDateTimeUpdate()
        );
    }

    private KeyPix make(final CreateKeyRequest createKeyRequest){
        return KeyPixFactory.create(
                createKeyRequest.getName(),
                createKeyRequest.getType(),
                createKeyRequest.getValue(),
                createKeyRequest.getAccountType(),
                createKeyRequest.getAgencyNumber(),
                createKeyRequest.getAccountNumber(),
                createKeyRequest.getAccountHolderName(),
                createKeyRequest.getAccountHolderLastName(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );
    }

    private GetKeyResponse convert(final KeyPix keyPix) {
        return GetKeyResponseFactory.create(
                keyPix.getId(),
                keyPix.getName(),
                keyPix.getType(),
                keyPix.getValue(),
                keyPix.getAccountType(),
                keyPix.getAgencyNumber(),
                keyPix.getAccountNumber(),
                keyPix.getAccountHolderName(),
                keyPix.getAccountHolderLastName(),
                keyPix.getDateTimeInclusion(),
                keyPix.getDateTimeUpdate()
        );
    }

    private void verifyQuantityPF(final String agencyNumber, final String accountNumber) throws ExceededQuantityException {
        int result = keyRepository.countByAccount(agencyNumber, accountNumber);
        if(result > 4) {
            throw new ExceededQuantityException();
        }
    }

    private void verifyQuantityPJ(final String agencyNumber, final String accountNumber) throws ExceededQuantityException {
        if(keyRepository.countByAccount(agencyNumber, accountNumber) > 19) {
            throw new ExceededQuantityException();
        }
    }


}

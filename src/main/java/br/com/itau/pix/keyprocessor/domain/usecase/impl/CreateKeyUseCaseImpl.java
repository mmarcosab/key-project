package br.com.itau.pix.keyprocessor.domain.usecase.impl;

import br.com.itau.pix.keyprocessor.domain.AccountValidator;
import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.KeyValidator;
import br.com.itau.pix.keyprocessor.domain.exception.ExceededQuantityException;
import br.com.itau.pix.keyprocessor.domain.factory.KeyPixFactory;
import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.CreateKeyUseCase;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyResponse;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class CreateKeyUseCaseImpl implements CreateKeyUseCase {

    private final KeyRepository keyRepository;
    private final KeyPixPresenter keyPixPresenter;
    private final AccountTypeRepository accountTypeRepository;

    private final Logger logger = Logger.getLogger(CreateKeyUseCaseImpl.class.getName());

    public CreateKeyUseCaseImpl(
            final KeyRepository keyRepository,
            final KeyPixPresenter keyPixPresenter,
            final AccountTypeRepository accountTypeRepository) {
        this.keyRepository = keyRepository;
        this.keyPixPresenter = keyPixPresenter;
        this.accountTypeRepository = accountTypeRepository;
    }

    public CreateKeyResponse create(final CreateKeyRequest createKeyRequest) {
        try {
            var keyPix = make(createKeyRequest);
            KeyValidator.isValid(keyPix.getType(), keyPix.getValue());
            AccountValidator.isValid(keyPix.getAccountType(), keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            if(keyRepository.existsByValue(keyPix.getValue())) {
                throw new KeyAlreadyExistsException();
            }
            final String typeAccount = accountTypeRepository.verifyTypeAccount(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            if("PF".equals(typeAccount)) {
                verifyQuantityPF(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            } else {
                verifyQuantityPJ(keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            }
            final String id = keyRepository.save(keyPix);
            return keyPixPresenter.prepareSuccessView(new CreateKeyResponse(id));
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareCreateKeyResponseFailView(e.getMessage());
        }

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

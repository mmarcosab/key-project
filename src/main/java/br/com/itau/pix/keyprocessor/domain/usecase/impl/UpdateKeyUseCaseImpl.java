package br.com.itau.pix.keyprocessor.domain.usecase.impl;

import br.com.itau.pix.keyprocessor.domain.AccountValidator;
import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.factory.KeyPixFactory;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.UpdateKeyUseCase;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.factory.UpdateKeyResponseFactory;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class UpdateKeyUseCaseImpl implements UpdateKeyUseCase {

    private final KeyRepository keyRepository;
    private final KeyPixPresenter keyPixPresenter;

    private final Logger logger = Logger.getLogger(UpdateKeyUseCaseImpl.class.getName());

    public UpdateKeyUseCaseImpl(final KeyRepository keyRepository, final KeyPixPresenter keyPixPresenter) {
        this.keyRepository = keyRepository;
        this.keyPixPresenter = keyPixPresenter;
    }

    public UpdateKeyResponse update(UpdateKeyRequest updateKeyRequest, String id) {
        try {
            var optionalKeyPix = keyRepository.findById(id);
            if(optionalKeyPix.isEmpty()) {
                return keyPixPresenter.prepareUpdateKeyResponseFailView("Key with this id don't exist");
            }
            var keyPix = optionalKeyPix.get();
            if(!keyPix.isActive()) {
                return keyPixPresenter.prepareUpdateKeyResponseFailView("Key is inactive");
            }
            var keyPixUpdated = createDomain(updateKeyRequest, keyPix);
            AccountValidator.isValid(keyPix.getAccountType(), keyPix.getAgencyNumber(), keyPix.getAccountNumber());
            keyRepository.save(keyPixUpdated);
            return keyPixPresenter.prepareSuccessView(createResponse(keyPixUpdated));
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
}

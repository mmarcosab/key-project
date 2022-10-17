package br.com.itau.pix.keyprocessor.domain.usecase.impl;

import br.com.itau.pix.keyprocessor.domain.exception.KeyAlreadyInactiveException;
import br.com.itau.pix.keyprocessor.domain.exception.KeyNotFoundException;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.InactivateKeyUseCase;
import br.com.itau.pix.keyprocessor.infra.rest.InactivateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.factory.InactivateKeyResponseFactory;

import java.util.logging.Logger;

public class InactivateKeyUseCaseImpl implements InactivateKeyUseCase {

    private final KeyRepository keyRepository;
    private final KeyPixPresenter keyPixPresenter;

    private final Logger logger = Logger.getLogger(InactivateKeyUseCaseImpl.class.getName());

    public InactivateKeyUseCaseImpl(final KeyRepository keyRepository, final KeyPixPresenter keyPixPresenter) {
        this.keyRepository = keyRepository;
        this.keyPixPresenter = keyPixPresenter;
    }

    public InactivateKeyResponse byId(final String id) {
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
                            keyPix.getDateTimeUpdate(),
                            keyPix.isActive()
                    )
            );

        } catch(KeyNotFoundException knf) {
            return keyPixPresenter.prepareInactiveKeyResponseFailView(knf.getMessage());
        } catch(KeyAlreadyInactiveException kai) {
            return keyPixPresenter.prepareInactiveKeyResponseFailView(kai.getMessage());
        } catch(Exception e) {
            logger.info("ERRO: " + e.getMessage());
            return keyPixPresenter.prepareInactiveKeyResponseFailView(e.getMessage());
        }

    }

}

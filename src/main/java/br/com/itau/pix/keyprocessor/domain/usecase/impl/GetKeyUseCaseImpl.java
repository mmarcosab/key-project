package br.com.itau.pix.keyprocessor.domain.usecase.impl;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.exception.KeyNotFoundException;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.GetKeyUseCase;
import br.com.itau.pix.keyprocessor.infra.rest.GetKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.factory.GetKeyResponseFactory;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GetKeyUseCaseImpl implements GetKeyUseCase {

    private final KeyRepository keyRepository;
    private final KeyPixPresenter keyPixPresenter;

    private final Logger logger = Logger.getLogger(GetKeyUseCaseImpl.class.getName());

    public GetKeyUseCaseImpl(final KeyRepository keyRepository, final KeyPixPresenter keyPixPresenter) {
        this.keyRepository = keyRepository;
        this.keyPixPresenter = keyPixPresenter;
    }

    public GetKeyResponse byId(final String id) {
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

    public GetKeyResponse byValue(final String value) {
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

    public List<GetKeyResponse> byCombinedFilter( final String type,
                                                   final String accountHolderName,
                                                   final String accountHolderLastName,
                                                   final String agencyNumber,
                                                   final String accountNumber) {
        try {
            var keyData = keyRepository.findByCombinedFilter(
                    type,
                    accountHolderName,
                    accountHolderLastName,
                    agencyNumber,
                    accountNumber
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

}

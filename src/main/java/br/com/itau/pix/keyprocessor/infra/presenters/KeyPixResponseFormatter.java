package br.com.itau.pix.keyprocessor.infra.presenters;

import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.GetKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.InactivateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
public class KeyPixResponseFormatter implements KeyPixPresenter {

    @Override
    public CreateKeyResponse prepareSuccessView(CreateKeyResponse createKeyResponse) {
        log.info("Preparing success view with message: {}", createKeyResponse);
        return createKeyResponse;
    }

    @Override
    public CreateKeyResponse prepareCreateKeyResponseFailView(String error) {
        log.error("Preparing fail view with message: {}", error);
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @Override
    public GetKeyResponse prepareSuccessView(GetKeyResponse getKeyResponse) {
        log.info("Preparing success view with message: {}", getKeyResponse);
        return getKeyResponse;
    }

    @Override
    public List<GetKeyResponse> prepareSuccessView(final List<GetKeyResponse> getKeyResponse) {
        log.info("Preparing success view with message: {}", getKeyResponse);
        return getKeyResponse;
    }

    @Override
    public List<GetKeyResponse> prepareListGetKeyResponseFailView(String error) {
        log.info("Preparing success view with message: {}", error);
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }
    @Override
    public GetKeyResponse prepareGetKeyResponseFailView(String error) {
        log.error("Preparing fail view with message: {}", error);
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @Override
    public InactivateKeyResponse prepareSuccessView(InactivateKeyResponse inactivateKeyResponse) {
        log.info("Preparing success view with message: {}", inactivateKeyResponse);
        return inactivateKeyResponse;
    }

    @Override
    public InactivateKeyResponse prepareInactiveKeyResponseFailView(String error) {
        log.error("Preparing fail view with message: {}", error);
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @Override
    public UpdateKeyResponse prepareSuccessView(UpdateKeyResponse updateKeyResponse) {
        log.info("Preparing success view with message: {}", updateKeyResponse);
        return updateKeyResponse;
    }

    @Override
    public UpdateKeyResponse prepareUpdateKeyResponseFailView(String error) {
        log.error("Preparing fail view with message: {}", error);
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @Override
    public UpdateKeyResponse prepareUpdateKeyNotFoundKeyResponseFailView() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public GetKeyResponse prepareGetNotFoundKeyResponseFailView() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<GetKeyResponse> prepareListGetNotFoundKeyResponseFailView() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public InactivateKeyResponse prepareInactivateNotFoundKeyResponseFailView() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}

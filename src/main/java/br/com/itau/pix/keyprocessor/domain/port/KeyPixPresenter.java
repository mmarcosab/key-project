package br.com.itau.pix.keyprocessor.domain.port;

import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.GetKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.InactivateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyResponse;

import java.util.List;


public interface KeyPixPresenter {
    CreateKeyResponse prepareSuccessView(CreateKeyResponse createKeyResponse);
    CreateKeyResponse prepareCreateKeyResponseFailView(String error);
    GetKeyResponse prepareSuccessView(GetKeyResponse getKeyResponse);
    List<GetKeyResponse> prepareSuccessView(final List<GetKeyResponse> getKeyResponse);
    GetKeyResponse prepareGetKeyResponseFailView(String error);
    List<GetKeyResponse> prepareListGetKeyResponseFailView(String error);
    InactivateKeyResponse prepareSuccessView(InactivateKeyResponse inactivateKeyResponse);
    InactivateKeyResponse prepareInactiveKeyResponseFailView(String error);
    UpdateKeyResponse prepareSuccessView(UpdateKeyResponse createKeyResponse);
    UpdateKeyResponse prepareUpdateKeyResponseFailView(String error);
    UpdateKeyResponse prepareUpdateKeyNotFoundKeyResponseFailView();
    GetKeyResponse prepareGetNotFoundKeyResponseFailView();
    CreateKeyResponse prepareCreateNotFoundKeyResponseFailView();
    InactivateKeyResponse prepareInactivateNotFoundKeyResponseFailView();

}

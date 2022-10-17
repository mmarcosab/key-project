package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyResponse;

public interface UpdateKeyUseCase {
    UpdateKeyResponse update(final UpdateKeyRequest updateKeyRequest, final String id);

}

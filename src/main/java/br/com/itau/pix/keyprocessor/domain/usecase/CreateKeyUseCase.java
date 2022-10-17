package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyResponse;

public interface CreateKeyUseCase {
    CreateKeyResponse create(final CreateKeyRequest createKeyRequest);

}

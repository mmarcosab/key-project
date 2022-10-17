package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.infra.rest.InactivateKeyResponse;

public interface InactivateKeyUseCase {
    InactivateKeyResponse byId(final String id);
}

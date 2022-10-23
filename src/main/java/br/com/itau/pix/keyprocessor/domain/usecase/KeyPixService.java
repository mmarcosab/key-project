package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.infra.rest.in.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.in.KeyFilterParam;
import br.com.itau.pix.keyprocessor.infra.rest.in.UpdateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.out.CreateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.out.GetKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.out.InactivateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.out.UpdateKeyResponse;

import java.util.List;

public interface KeyPixService {
    CreateKeyResponse create(CreateKeyRequest createKeyRequest);
    GetKeyResponse getById(final String id) ;
    List<GetKeyResponse> getByFilter(KeyFilterParam keyFilterParam);
    GetKeyResponse getByValue(final String value);
    InactivateKeyResponse inactivateById(final String id);
    UpdateKeyResponse update(final UpdateKeyRequest updateKeyRequest, final String id);
}

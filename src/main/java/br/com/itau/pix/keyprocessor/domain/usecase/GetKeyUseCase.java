package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.infra.rest.GetKeyResponse;
import java.util.List;

public interface GetKeyUseCase {
    GetKeyResponse byId(final String id);
    GetKeyResponse byValue(final String value);
    List<GetKeyResponse> byCombinedFilter( final String type,
                                           final String accountHolderName,
                                           final String accountHolderLastName,
                                           final String agencyNumber,
                                           final String accountNumber);
}

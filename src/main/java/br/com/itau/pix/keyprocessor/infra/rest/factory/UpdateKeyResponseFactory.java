package br.com.itau.pix.keyprocessor.infra.rest.factory;

import br.com.itau.pix.keyprocessor.infra.rest.out.UpdateKeyResponse;

import java.time.LocalDateTime;

public final class UpdateKeyResponseFactory {

    public static UpdateKeyResponse create(
            final String id,
            final String name,
            final String type,
            final String value,
            final String accountType,
            final String agencyNumber,
            final String accountNumber,
            final String accountHolderName,
            final String accountHolderLastName,
            final LocalDateTime dateTimeInclusion,
            final LocalDateTime dateTimeUpdate
    ) {
        return new UpdateKeyResponse(
                id,
                name,
                type,
                value,
                accountType,
                agencyNumber,
                accountNumber,
                accountHolderName,
                accountHolderLastName,
                dateTimeInclusion,
                dateTimeUpdate
        );
    }
}

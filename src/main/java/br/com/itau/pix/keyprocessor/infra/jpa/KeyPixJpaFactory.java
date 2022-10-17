package br.com.itau.pix.keyprocessor.infra.jpa;

import java.time.LocalDateTime;

public final class KeyPixJpaFactory {

    public static KeyPixJpa create(
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
            final LocalDateTime dateTimeUpdate,
            final Integer active
    ) {
        return new KeyPixJpa(
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
                dateTimeUpdate,
                active
        );
    }

}

package br.com.itau.pix.keyprocessor.domain.factory;

import br.com.itau.pix.keyprocessor.domain.KeyPix;

import java.time.LocalDateTime;

public final class KeyPixFactory {

    public static KeyPix create(
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
            final Integer active) {
        return new KeyPix(
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

    public static KeyPix create(
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
            final Integer active) {
        return new KeyPix(
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

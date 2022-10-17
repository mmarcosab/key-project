package br.com.itau.pix.keyprocessor.infra.repository;

import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class KeyPixJpaBuilderTest {

    @Test
    public void testBuilder(){
        Assertions.assertDoesNotThrow(() -> KeyPixJpaBuilder
                .builder()
                .addType("type")
                .addAccountHolderName("accountHolderName")
                .addAgencyNumber("agencyNumber")
                .addAccountNumber("accountNumber")
                .addActive(1)
                .addDateTimeInclusion(LocalDateTime.now())
                .addDateTimeUpdate(LocalDateTime.now())
                .addAccountHolderLastName("test")
                .addAccountType("test")
                .addValue("test")
                .addName("test")
                .build());
    }

}

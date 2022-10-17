package br.com.itau.pix.keyprocessor.infra.rest.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InactiveKeyResponseFactoryTest {

    @Test
    public void testFactoryWithId() {
        var keyResponse  = InactivateKeyResponseFactory.create(
                "31bc8767-0c2a-48e1-84a2-37790230a418",
                "my-key-1",
                "teste",
                "teste@teste.com",
                "corrente",
                "corrente",
                "1122",
                "123456",
                "marcos",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        var keyResponse2 = InactivateKeyResponseFactory.create(
                "31bc8767-0c2a-48e1-84a2-37790230a417",
                "my-key-2",
                "teste",
                "teste@teste.com",
                "corrente",
                "corrente",
                "1122",
                "123456",
                "marcos",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        Assertions.assertNotEquals(keyResponse.getName(), keyResponse2.getName());
    }

}

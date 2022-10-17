package br.com.itau.pix.keyprocessor.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class KeyPixFactoryTest {

    @Test
    public void testFactoryWithoutId() {
        var keyPix = KeyPixFactory.create(
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
                1
        );
        var keyPix2 = KeyPixFactory.create(
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
                1
        );
        Assertions.assertNotEquals(keyPix.getName(), keyPix2.getName());
    }

    @Test
    public void testFactoryWithId() {
        var keyPix = KeyPixFactory.create(
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
                1
        );
        var keyPix2 = KeyPixFactory.create(
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
                1
        );
        Assertions.assertNotEquals(keyPix.getName(), keyPix2.getName());
    }


}

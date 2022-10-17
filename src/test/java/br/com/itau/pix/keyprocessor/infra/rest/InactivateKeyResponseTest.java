package br.com.itau.pix.keyprocessor.infra.rest;

import br.com.itau.pix.keyprocessor.infra.rest.factory.InactivateKeyResponseFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InactivateKeyResponseTest {

    @Test
    public void testInactivateKeyResponseValue(){
        var dateTime = LocalDateTime.now();
        var getKeyResponse  = InactivateKeyResponseFactory.create(
                "31bc8767-0c2a-48e1-84a2-37790230a418",
                "my-key-1",
                "teste",
                "teste@teste.com",
                "corrente",
                "1122",
                "112233",
                "teste",
                "marcos",
                dateTime,
                dateTime,
                true
        );
        assertEquals("31bc8767-0c2a-48e1-84a2-37790230a418", getKeyResponse.getId());
        assertEquals("my-key-1", getKeyResponse.getName());
        assertEquals("teste", getKeyResponse.getType());
        assertEquals("teste@teste.com", getKeyResponse.getValue());
        assertEquals("corrente", getKeyResponse.getAccountType());
        assertEquals("1122", getKeyResponse.getAgencyNumber());
        assertEquals("teste", getKeyResponse.getAccountHolderName());
        assertEquals("marcos", getKeyResponse.getAccountHolderLastName());
        assertEquals("112233", getKeyResponse.getAccountNumber());
        assertEquals(dateTime, getKeyResponse.getDateTimeInclusion());
        assertEquals(dateTime, getKeyResponse.getDateTimeUpdate());
        assertEquals(true, getKeyResponse.isActive());
    }
}

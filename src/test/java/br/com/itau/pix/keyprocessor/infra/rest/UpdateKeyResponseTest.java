package br.com.itau.pix.keyprocessor.infra.rest;

import br.com.itau.pix.keyprocessor.infra.rest.factory.UpdateKeyResponseFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateKeyResponseTest {

    @Test
    public void testGetKeyResponseValue(){
        var dateTime = LocalDateTime.now();
        var updateKeyResponse  = UpdateKeyResponseFactory.create(
                "31bc8767-0c2a-48e1-84a2-37790230a418",
                "my-key-1",
                "teste",
                "teste@teste.com",
                "corrente",
                "corrente",
                "1122",
                "123456",
                "marcos",
                dateTime,
                dateTime
        );
        assertEquals("31bc8767-0c2a-48e1-84a2-37790230a418", updateKeyResponse.getId());
        assertEquals("my-key-1", updateKeyResponse.getName());
        assertEquals("teste", updateKeyResponse.getType());
        assertEquals("teste@teste.com", updateKeyResponse.getValue());
        assertEquals("corrente", updateKeyResponse.getAccountType());
        assertEquals("corrente", updateKeyResponse.getAgencyNumber());
        assertEquals("123456", updateKeyResponse.getAccountHolderName());
        assertEquals("marcos", updateKeyResponse.getAccountHolderLastName());
        assertEquals("1122", updateKeyResponse.getAccountNumber());
        assertEquals(dateTime, updateKeyResponse.getDateTimeInclusion());
        assertEquals(dateTime, updateKeyResponse.getDateTimeUpdate());
    }
}

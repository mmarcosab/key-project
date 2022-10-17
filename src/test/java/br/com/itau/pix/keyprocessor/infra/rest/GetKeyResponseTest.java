package br.com.itau.pix.keyprocessor.infra.rest;

import br.com.itau.pix.keyprocessor.infra.rest.factory.GetKeyResponseFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GetKeyResponseTest {

    @Test
    public void testGetKeyResponseValue(){
        var dateTime = LocalDateTime.now();
        var getKeyResponse  = GetKeyResponseFactory.create(
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
        assertEquals("31bc8767-0c2a-48e1-84a2-37790230a418", getKeyResponse.getId());
        assertEquals("my-key-1", getKeyResponse.getName());
        assertEquals("teste", getKeyResponse.getType());
        assertEquals("teste@teste.com", getKeyResponse.getValue());
        assertEquals("corrente", getKeyResponse.getAccountType());
        assertEquals("corrente", getKeyResponse.getAgencyNumber());
        assertEquals("123456", getKeyResponse.getAccountHolderName());
        assertEquals("marcos", getKeyResponse.getAccountHolderLastName());
        assertEquals("1122", getKeyResponse.getAccountNumber());
        assertEquals(dateTime, getKeyResponse.getDateTimeInclusion());
        assertEquals(dateTime, getKeyResponse.getDateTimeUpdate());
    }
}

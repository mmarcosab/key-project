package br.com.itau.pix.keyprocessor.infra.formatter;

import br.com.itau.pix.keyprocessor.infra.presenters.KeyPixResponseFormatter;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.GetKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.InactivateKeyResponse;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KeyPixResponseFormatterTest {

    private KeyPixResponseFormatter keyPixResponseFormatter;

    @BeforeEach
    public void init() {
        keyPixResponseFormatter = new KeyPixResponseFormatter();
    }

    @Test
    public void testCreateKeyResponsePrepareSuccessView() {
        Assertions.assertDoesNotThrow(() -> keyPixResponseFormatter.prepareSuccessView(mockCreateKeyResponse()));
    }

    @Test
    public void testPrepareCreateKeyResponseFailView() {
        assertThrows(ResponseStatusException.class, () -> keyPixResponseFormatter.prepareCreateKeyResponseFailView(""));
    }

    @Test
    public void testGetKeyResponsePrepareSuccessView() {
        Assertions.assertDoesNotThrow(() -> keyPixResponseFormatter.prepareSuccessView(Mockito.mock(GetKeyResponse.class)));
    }

    @Test
    public void testListGetKeyResponsePrepareSuccessView() {
        assertDoesNotThrow(() -> keyPixResponseFormatter.prepareSuccessView(Mockito.mock(List.class)));
    }

    @Test
    public void testPrepareGetKeyResponseFailView() {
        assertThrows(ResponseStatusException.class, () -> keyPixResponseFormatter.prepareGetKeyResponseFailView(""));
    }


    @Test
    public void testPrepareListGetKeyResponseFailView() {
        assertThrows(ResponseStatusException.class, () -> keyPixResponseFormatter.prepareListGetKeyResponseFailView(""));
    }

    @Test
    public void testInactivatetKeyResponsePrepareSuccessView() {
        Assertions.assertDoesNotThrow(() -> keyPixResponseFormatter.prepareSuccessView(Mockito.mock(InactivateKeyResponse.class)));
    }


    @Test
    public void testPrepareInactivateGetKeyResponseFailView() {
        assertThrows(ResponseStatusException.class, () -> keyPixResponseFormatter.prepareInactiveKeyResponseFailView(""));
    }

    @Test
    public void testUpdateKeyResponsePrepareSuccessView() {
        Assertions.assertDoesNotThrow(() -> keyPixResponseFormatter.prepareSuccessView(Mockito.mock(UpdateKeyResponse.class)));
    }

    @Test
    public void testPrepareUpdateKeyResponseFailView() {
        assertThrows(ResponseStatusException.class, () -> keyPixResponseFormatter.prepareUpdateKeyResponseFailView("test"));
    }

    private CreateKeyResponse mockCreateKeyResponse () {
        return new CreateKeyResponse("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2");
    }


}

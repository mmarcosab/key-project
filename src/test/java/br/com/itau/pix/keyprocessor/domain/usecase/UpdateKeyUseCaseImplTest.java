package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.UpdateKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class UpdateKeyUseCaseImplTest {

    private KeyRepository keyRepository;
    private KeyPixPresenter keyPixPresenter;
    private UpdateKeyUseCaseImpl updateKeyUseCase;

    @BeforeEach
    public void init() {
        keyRepository = mock(KeyRepository.class);
        keyPixPresenter = mock(KeyPixPresenter.class);
        updateKeyUseCase = new UpdateKeyUseCaseImpl(keyRepository, keyPixPresenter);
    }

    @Test
    public void givenARequestThenThrowAnException() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.of(mockKeyPix()));
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(4);
        assertDoesNotThrow(() -> updateKeyUseCase.update(mockRequest(), "test"));
    }

    @Test
    public void givenARequestThenUpdateAKey() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.of(mockCompleteKeyPix()));
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(4);
        assertDoesNotThrow(() -> updateKeyUseCase.update(mockRequest(), "test"));
    }

    @Test
    public void givenARequestThenReturnAInactiveKey() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.of(mockCompleteInactiveKeyPix()));
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(4);
        assertDoesNotThrow(() -> updateKeyUseCase.update(mockRequest(), "test"));
    }

    @Test
    public void givenARequestThenReturNoKey() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.empty());
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(4);
        assertDoesNotThrow(() -> updateKeyUseCase.update(mockRequest(), "test"));
    }

    private KeyPix mockKeyPix() {
        return new KeyPix(
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "aleatoria",
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "corrente",
                "1234",
                "123456",
                "teste",
                "da silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );
    }

    private KeyPix mockCompleteKeyPix() {
        return new KeyPix(
                "1234",
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "aleatoria",
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "corrente",
                "1234",
                "123456",
                "teste",
                "da silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );
    }

    private KeyPix mockCompleteInactiveKeyPix() {
        return new KeyPix(
                "1234",
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "aleatoria",
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "corrente",
                "1234",
                "123456",
                "teste",
                "da silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                0
        );
    }
    private UpdateKeyRequest mockRequest() {
        return new UpdateKeyRequest(
                "s69d-6cb-0022-41ae-aa4c-2799fa6c22e2",
                "1234",
                "123456",
                "corrente",
                "teste",
                "da silva"
        );
    }
}

package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.KeyPixServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetKeyUseCaseImplTest {

    private KeyRepository keyRepository;
    private KeyPixPresenter keyPixPresenter;
    private KeyPixServiceImpl keyPixService;
    private AccountTypeRepository accountTypeRepository;

    @BeforeEach
    public void init() {
        keyRepository = mock(KeyRepository.class);
        keyPixPresenter = mock(KeyPixPresenter.class);
        accountTypeRepository = mock(AccountTypeRepository.class);
        keyPixService = new KeyPixServiceImpl(keyRepository, keyPixPresenter, accountTypeRepository);
    }

    @Test
    public void givenAnIdThenReturnAKey() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.of(mockKeyPix()));
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAIdThenReturnAFail() {
        when(keyRepository.findByValue(anyString())).thenReturn(null);
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAIdThenReturnAFailBecauseOfAnEmpty() {
        when(keyRepository.findById(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAValueThenReturnAKey() {
        when(keyRepository.findByValue(anyString())).thenReturn(Optional.of(mockKeyPix()));
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAValueThenReturnAFail() {
        when(keyRepository.findByValue(anyString())).thenReturn(null);
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAValueThenReturnAFailBecauseOfAnEmpty() {
        when(keyRepository.findByValue(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> keyPixService.getById("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    private KeyPix mockKeyPix() {
        return new KeyPix(
                "s69d.6cb-0022-41ae-aa4c-2799fa6c22e2",
                "aleatoria",
                "s69d.6cb-0022-41ae-aa4c-2799fa6c22e2",
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
}

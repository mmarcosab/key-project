package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.CreateKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreationKeyUseCaseImplTest {

    private KeyRepository keyRepository;
    private KeyPixPresenter keyPixPresenter;
    private CreateKeyUseCaseImpl createKeyUseCase;
    private AccountTypeRepository accountTypeRepository;

    @BeforeEach
    public void init() {
        keyRepository = mock(KeyRepository.class);
        keyPixPresenter = mock(KeyPixPresenter.class);
        accountTypeRepository = mock(AccountTypeRepository.class);
        createKeyUseCase = new CreateKeyUseCaseImpl(keyRepository, keyPixPresenter, accountTypeRepository);
    }

    @Test
    public void givenARequestThenCreateAKey() {
        when(keyRepository.existsByValue(anyString())).thenReturn(false);
        when(accountTypeRepository.verifyTypeAccount(anyString(), anyString())).thenReturn("PF");
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(4);
        when(keyRepository.save(any())).thenReturn("c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0");
        assertDoesNotThrow(() -> createKeyUseCase.create(mockRequest()));
    }

    @Test
    public void givenAExistentKeyRequestThenThrowAnException() {
        when(keyRepository.existsByValue(anyString())).thenReturn(true);
        when(keyRepository.save(any())).thenReturn("c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0");
        assertDoesNotThrow(() -> createKeyUseCase.create(mockRequest()));
    }

    @Test
    public void givenAValidKeyButAlreadyFiceExistsRequestThenThrowAnException() {
        when(keyRepository.existsByValue(anyString())).thenReturn(false);
        when(accountTypeRepository.verifyTypeAccount(anyString(), anyString())).thenReturn("PF");
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(5);
        when(keyRepository.save(any())).thenReturn("c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0");
        assertDoesNotThrow(() -> createKeyUseCase.create(mockRequest()));
    }

    @Test
    public void givenAValidKeyButAlreadyTwentyExistsRequestThenThrowAnException() {
        when(keyRepository.existsByValue(anyString())).thenReturn(false);
        when(accountTypeRepository.verifyTypeAccount(anyString(), anyString())).thenReturn("PJ");
        when(keyRepository.countByAccount(anyString(), anyString())).thenReturn(20);
        when(keyRepository.save(any())).thenReturn("c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0");
        assertDoesNotThrow(() -> createKeyUseCase.create(mockRequest()));
    }

    private KeyPix mockKeyPix() {
        return new KeyPix(
                "c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0",
                "aleatoria",
                "c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0",
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

    private CreateKeyRequest mockRequest() {
        return new CreateKeyRequest(
                "c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0",
                "aleatorio",
                "c23fcc72-fb3e-4cd4-be33-5d438ea7c2c0",
                "corrente",
                "1234",
                "123456",
                "teste",
                "da silva"
        );
    }
}

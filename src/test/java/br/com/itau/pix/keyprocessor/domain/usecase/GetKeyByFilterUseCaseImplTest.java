package br.com.itau.pix.keyprocessor.domain.usecase;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.KeyPixServiceImpl;
import br.com.itau.pix.keyprocessor.infra.rest.in.KeyFilterParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class GetKeyByFilterUseCaseImplTest {

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
    public void givenACombinedFilterThenReturnAKey() {
        when(keyRepository.findByCombinedFilter(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(List.of(mockKeyPix()));
        assertDoesNotThrow(() -> keyPixService.getByFilter(new KeyFilterParam("test", "test", "test", "test", "test")));
    }

    @Test
    public void givenACombinedFilterThenThrowAnException() {
        when(keyRepository.findByCombinedFilter(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        assertDoesNotThrow(() -> keyPixService.getByFilter(new KeyFilterParam("test", "test", "test", "test", "test")));
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

package br.com.itau.pix.keyprocessor.infra.repository;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MySqlDBKeyPixRepositoryTest {

    private SpringDataKeyPixRepository springDataKeyPixRepository;
    private MySqlDBKeyPixRepository mySqlDBKeyPixRepository;

    @BeforeEach
    public void init() {
        springDataKeyPixRepository = mock(SpringDataKeyPixRepository.class);
        mySqlDBKeyPixRepository = new MySqlDBKeyPixRepository(springDataKeyPixRepository);
    }
    @Test
    public void givenAnIdThenReturnAKey() {
        when(springDataKeyPixRepository.findById(anyString())).thenReturn(Optional.of(mockKeyPixJpa()));
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.findById("test"));
    }

    @Test
    public void givenAKeyDataThenSaveAKey() {
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.save(mockKeyPix()));
    }

    @Test
    public void givenAnAccountNumberThenReturnACountedValue() {
        when(springDataKeyPixRepository.countByAccount(anyString(), anyString())).thenReturn(2);
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.countByAccount("test", "test"));
    }

    @Test
    public void givenAValueThenReturnKey() {
        when(springDataKeyPixRepository.findByValue(anyString())).thenReturn(Optional.of(mockKeyPixJpa()));
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.findByValue("test"));
    }

    @Test
    public void givenAIdThenInactivateAKey() {
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.inactivateById(mockKeyPix()));
    }

    @Test
    public void givenAIdThenReturnABoolean() {
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.existsByValue("test"));
    }

    @Test
    public void givenAnFilterThenReturnAKeyListe() {
        when(springDataKeyPixRepository.findAll(any(Example.class))).thenReturn(List.of(mockKeyPixJpa()));
        assertDoesNotThrow(() -> mySqlDBKeyPixRepository.findByCombinedFilter(
                "test",
                "test",
                "test",
                "test",
                "test"));
    }

    private KeyPixJpa mockKeyPixJpa() {
        return new KeyPixJpa(
                "s69d.6cb-0022-41ae-aa4c-2799fa6c22e2",
                "aleatoria",
                "aleatoria",
                "s69d.6cb-0022-41ae-aa4c-2799fa6c22e2",
                "corrente",
                "1234",
                "123456",
                "marcos",
                "da silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );
    }

    private KeyPix mockKeyPix() {
        return new KeyPix(
                "s69d.6cb-0022-41ae-aa4c-2799fa6c22e2",
                "test",
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

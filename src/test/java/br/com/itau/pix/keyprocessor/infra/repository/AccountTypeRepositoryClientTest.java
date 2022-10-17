package br.com.itau.pix.keyprocessor.infra.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AccountTypeRepositoryClientTest {

    private AccountTypeRepositoryClient accountTypeRepositoryClient;

    @BeforeEach
    public void init() {
        accountTypeRepositoryClient = new AccountTypeRepositoryClient();
    }

    @Test
    public void givenAValueThenReturnPF() {
        assertDoesNotThrow(() -> accountTypeRepositoryClient.verifyTypeAccount("5", "5"));
    }

    @Test
    public void givenAValueThenReturnPJ() {
        assertDoesNotThrow(() -> accountTypeRepositoryClient.verifyTypeAccount("8", "8"));
    }

}

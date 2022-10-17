package br.com.itau.pix.keyprocessor.domain.port;

public interface AccountTypeRepository {
    String verifyTypeAccount(final String accountAgency, final String accountNumber);
}

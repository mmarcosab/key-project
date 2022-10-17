package br.com.itau.pix.keyprocessor.infra.repository;

import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;

public class AccountTypeRepositoryClient implements AccountTypeRepository {

    @Override
    public String verifyTypeAccount(final String accountAgency, final String accountNumber) {
        return accountAgency.startsWith("5") || accountAgency.startsWith("6")? "PF" : "PJ";
    }

}

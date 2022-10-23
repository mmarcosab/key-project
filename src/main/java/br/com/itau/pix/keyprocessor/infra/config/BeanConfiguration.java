package br.com.itau.pix.keyprocessor.infra.config;

import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.KeyPixService;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.KeyPixServiceImpl;
import br.com.itau.pix.keyprocessor.infra.presenters.KeyPixResponseFormatter;
import br.com.itau.pix.keyprocessor.infra.repository.AccountTypeRepositoryClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    @Qualifier("createKeyPixResponseFormatter")
    public KeyPixResponseFormatter createKeyPixResponseFormatter() {
        return new KeyPixResponseFormatter();
    }

    @Bean
    @Qualifier("accountTypeRepositoryClient")
    public AccountTypeRepositoryClient accountTypeRepositoryClient() {
        return new AccountTypeRepositoryClient();
    }

    @Bean
    @Qualifier("keyPixService")
    public KeyPixService keyPixService(final KeyRepository repository, final KeyPixPresenter keyPixPresenter, final AccountTypeRepository accountTypeRepository) {
        return new KeyPixServiceImpl(repository, keyPixPresenter, accountTypeRepository);
    }

}

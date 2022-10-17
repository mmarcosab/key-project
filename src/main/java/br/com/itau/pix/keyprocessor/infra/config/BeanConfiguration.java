package br.com.itau.pix.keyprocessor.infra.config;

import br.com.itau.pix.keyprocessor.domain.port.AccountTypeRepository;
import br.com.itau.pix.keyprocessor.domain.port.KeyPixPresenter;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.domain.usecase.CreateKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.GetKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.InactivateKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.UpdateKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.CreateKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.GetKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.InactivateKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.domain.usecase.impl.UpdateKeyUseCaseImpl;
import br.com.itau.pix.keyprocessor.infra.presenters.KeyPixResponseFormatter;
import br.com.itau.pix.keyprocessor.infra.repository.AccountTypeRepositoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    public KeyPixResponseFormatter createKeyPixResponseFormatter() {
        return new KeyPixResponseFormatter();
    }

    @Bean
    public AccountTypeRepositoryClient accountTypeRepositoryClient() {
        return new AccountTypeRepositoryClient();
    }

    @Bean
    public CreateKeyUseCase createKeyUseCase(final KeyRepository repository, final KeyPixPresenter keyPixPresenter, final AccountTypeRepository accountTypeRepository) {
        return new CreateKeyUseCaseImpl(repository, keyPixPresenter, accountTypeRepository);
    }

    @Bean
    public GetKeyUseCase getKeyUseCase(final KeyRepository repository, final KeyPixPresenter keyPixPresenter) {
        return new GetKeyUseCaseImpl(repository, keyPixPresenter);
    }

    @Bean
    public InactivateKeyUseCase inactivateKeyUseCase(final KeyRepository repository, final KeyPixPresenter keyPixPresenter) {
        return new InactivateKeyUseCaseImpl(repository, keyPixPresenter);
    }

    @Bean
    public UpdateKeyUseCase updateKeyUseCase(final KeyRepository keyRepository, final KeyPixPresenter keyPixPresenter) {
        return new UpdateKeyUseCaseImpl(keyRepository, keyPixPresenter);
    }
}

package br.com.itau.pix.keyprocessor.infra.config;

import br.com.itau.pix.keyprocessor.infra.repository.SpringDataKeyPixRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataKeyPixRepository.class)
public class MySqlDbConfiguration {
    //TODO create a bean of config db
}

package br.com.itau.pix.keyprocessor.infra.repository;

import br.com.itau.pix.keyprocessor.domain.KeyPix;
import br.com.itau.pix.keyprocessor.domain.factory.KeyPixFactory;
import br.com.itau.pix.keyprocessor.domain.port.KeyRepository;
import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpa;
import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpaBuilder;
import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpaFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MySqlDBKeyPixRepository implements KeyRepository {

    private final SpringDataKeyPixRepository springDataKeyPixRepository;

    public MySqlDBKeyPixRepository(SpringDataKeyPixRepository springDataKeyPixRepository) {
        this.springDataKeyPixRepository = springDataKeyPixRepository;
    }

    @Override
    public Optional<KeyPix> findById(final String id) {
        var keyPixOptionalData = springDataKeyPixRepository.findById(id);
        if(keyPixOptionalData.isEmpty())
            return Optional.empty();
        var keyPixData = keyPixOptionalData.get();
        return Optional.of(convertToDomain(keyPixData));
    }

    @Override
    public boolean existsByValue(final String value) {
        return springDataKeyPixRepository.existsByValue(value);
    }

    @Override
    public String save(final KeyPix keyPix) {
        final KeyPixJpa keyPixJpa= convertToJpa(keyPix);
        springDataKeyPixRepository.save(keyPixJpa);
        return keyPixJpa.getId();
    }

    @Override
    public void inactivateById(final KeyPix keyPix) {
        final KeyPixJpa keyPixJpa=  convertToJpa(keyPix);
        springDataKeyPixRepository.save(keyPixJpa);
    }

    @Override
    public Optional<KeyPix> findByValue(final String value) {
        var keyPixOptionalData = springDataKeyPixRepository.findByValue(value);
        if(keyPixOptionalData.isEmpty())
            return Optional.empty();
        var keyPixData = keyPixOptionalData.get();
        return Optional.of(convertToDomain(keyPixData));
    }

    @Override
    public Integer countByAccount(final String agencyNumber, final String accountNumber){
        return springDataKeyPixRepository.countByAccount(agencyNumber, accountNumber);
    }

    @Override
    public List<KeyPix> findByCombinedFilter(
            final String type,
            final String accountHolderName,
            final String accountHolderLastName,
            final String agencyNumber,
            final String accountNumber) {

        final KeyPixJpa keyPixJpa = KeyPixJpaBuilder
                .builder()
                .addType(type)
                .addAccountHolderName(accountHolderName)
                .addAccountHolderLastName(accountHolderLastName)
                .addAgencyNumber(agencyNumber)
                .addAccountNumber(accountNumber)
                .addActive(1)
                .build();

        var keyPixListData = springDataKeyPixRepository.findAll(Example.of(keyPixJpa));

        return keyPixListData.stream()
                .map(key -> convertToDomain(key))
                .collect(Collectors.toList());

    }

    private KeyPixJpa convertToJpa(final KeyPix keyPix) {
        return KeyPixJpaFactory.create(
                keyPix.getId(),
                keyPix.getName(),
                keyPix.getType(),
                keyPix.getValue(),
                keyPix.getAccountType(),
                keyPix.getAgencyNumber(),
                keyPix.getAccountNumber(),
                keyPix.getAccountHolderName(),
                keyPix.getAccountHolderLastName(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                keyPix.isActive()? 1 : 0);

    }

    private KeyPix convertToDomain(final KeyPixJpa keyPixData) {
        return KeyPixFactory.create(
                keyPixData.getId(),
                keyPixData.getName(),
                keyPixData.getType(),
                keyPixData.getValue(),
                keyPixData.getAccountType(),
                keyPixData.getAgencyNumber(),
                keyPixData.getAccountNumber(),
                keyPixData.getAccountHolderName(),
                keyPixData.getAccountHolderLastName(),
                keyPixData.getDateTimeInclusion(),
                keyPixData.getDateTimeUpdate(),
                keyPixData.getActive()
        );
    }
}

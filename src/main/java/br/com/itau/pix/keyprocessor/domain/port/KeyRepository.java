package br.com.itau.pix.keyprocessor.domain.port;

import br.com.itau.pix.keyprocessor.domain.KeyPix;

import java.util.List;
import java.util.Optional;

public interface KeyRepository {
    Optional<KeyPix> findById(final String id);
    boolean existsByValue(final String value);
    String save(final KeyPix keyPix);
    void inactivateById(final KeyPix keyPix);
    Integer countByAccount(final String agencyNumber, final String accountNumber);
    Optional<KeyPix> findByValue(final String value);
    List<KeyPix> findByCombinedFilter(
            final String type,
            final String accountHolderName,
            final String accountHolderLastName,
            final String agencyNumber,
            final String accountNumber);
}

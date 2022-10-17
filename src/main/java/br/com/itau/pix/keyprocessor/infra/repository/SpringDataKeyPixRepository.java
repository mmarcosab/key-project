package br.com.itau.pix.keyprocessor.infra.repository;

import br.com.itau.pix.keyprocessor.infra.jpa.KeyPixJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataKeyPixRepository extends JpaRepository<KeyPixJpa, String> {
    boolean existsByValue(final String value);
    @Query("SELECT k FROM KeyPixJpa k WHERE k.value = :value AND k.active = 1")
    Optional<KeyPixJpa> findByValue(final String value);
    @Query("SELECT k FROM KeyPixJpa k WHERE k.accountHolderName = :accountHolderName AND k.active = 1")
    List<KeyPixJpa> findByName(final String accountHolderName);
    @Query("SELECT k FROM KeyPixJpa k WHERE k.id = :id AND k.active = 1")
    Optional<KeyPixJpa> findById(final String id);
    @Query("SELECT COUNT(k) FROM KeyPixJpa k WHERE k.agencyNumber = :agencyNumber AND k.accountNumber = :accountNumber")
    Integer countByAccount(final String agencyNumber, final String accountNumber);
    @Query("SELECT k FROM KeyPixJpa k WHERE k.accountNumber = :accountNumber AND k.agencyNumber = :agencyNumber")
    List<KeyPixJpa> findByAgencyAndAccountNumber(final String agencyNumber, final String accountNumber);
}

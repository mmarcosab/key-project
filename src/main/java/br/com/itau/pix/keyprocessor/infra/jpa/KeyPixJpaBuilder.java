package br.com.itau.pix.keyprocessor.infra.jpa;

import java.time.LocalDateTime;

public class KeyPixJpaBuilder {

    private KeyPixJpa keyPixJpa;

    public KeyPixJpaBuilder() {
        this.keyPixJpa = new KeyPixJpa();
    }

    public static KeyPixJpaBuilder builder() {
        return new KeyPixJpaBuilder();
    }

    public KeyPixJpaBuilder addName(final String nome) {
        this.keyPixJpa.setName(nome);
        return this;
    }

    public KeyPixJpaBuilder addType(final String type) {
        this.keyPixJpa.setType(type);
        return this;
    }

    public KeyPixJpaBuilder addValue(final String value) {
        this.keyPixJpa.setValue(value);
        return this;
    }

    public KeyPixJpaBuilder addAccountType(final String accountType) {
        this.keyPixJpa.setAccountType(accountType);
        return this;
    }

    public KeyPixJpaBuilder addAgencyNumber(final String agencyNumber) {
        this.keyPixJpa.setAgencyNumber(agencyNumber);
        return this;
    }

    public KeyPixJpaBuilder addAccountNumber(final String accountNumber) {
        this.keyPixJpa.setAccountNumber(accountNumber);
        return this;
    }

    public KeyPixJpaBuilder addAccountHolderName(final String accountHolderName) {
        this.keyPixJpa.setAccountHolderName(accountHolderName);
        return this;
    }

    public KeyPixJpaBuilder addAccountHolderLastName(final String accountHolderLastName) {
        this.keyPixJpa.setAccountHolderLastName(accountHolderLastName);
        return this;
    }

    public KeyPixJpaBuilder addDateTimeInclusion(final LocalDateTime dateTimeInclusion) {
        this.keyPixJpa.setDateTimeInclusion(dateTimeInclusion);
        return this;
    }

    public KeyPixJpaBuilder addDateTimeUpdate(final LocalDateTime dateTimeUpdate) {
        this.keyPixJpa.setDateTimeUpdate(dateTimeUpdate);
        return this;
    }

    public KeyPixJpaBuilder addActive(final Integer active) {
        this.keyPixJpa.setActive(active);
        return this;
    }

    public KeyPixJpa build() {
        return this.keyPixJpa;
    }

}

package br.com.itau.pix.keyprocessor.domain;

import br.com.itau.pix.keyprocessor.domain.exception.InvalidAccountException;
import br.com.itau.pix.keyprocessor.domain.exception.InvalidAccountTypeException;
import br.com.itau.pix.keyprocessor.domain.exception.InvalidAgencyException;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum AccountValidator {
    POUPANCA_TYPE("poupanca"),
    CORRENTE_TYPE("corrente")
    ;

    private String accountType;

    AccountValidator(String accountType){
        this.accountType = accountType;
    }
    public static void isValid(final String type, final String agency, final String account) throws Exception{
        verifyIfIsValidAccountType(type);
        verifyIfIsValidAgency(agency);
        verifyIfIsValidAccount(account);
    }


    public static void verifyIfIsValidAccountType(final String type) throws InvalidAccountTypeException {
        if(type == null) {
            throw new InvalidAccountTypeException();
        }
        if(type.length() > 10) {
            throw new InvalidAccountTypeException();
        }
        if(!isValidType(type)) {
            throw new InvalidAccountTypeException();
        }
    }

    private static boolean isValidType(final String type) {
        return Arrays.stream(AccountValidator.values())
                .filter(a -> a.getAccountType().equals(type))
                .findAny().isPresent();
    }

    public static void verifyIfIsValidAgency(final String agency) throws InvalidAgencyException {
        if(agency == null) {
            throw new InvalidAgencyException();
        }
        final Pattern pattern = Pattern.compile("^[0-9]{4}");
        if(!pattern.matcher(agency).matches()) {
            throw new InvalidAgencyException();
        }
    }

    public static void verifyIfIsValidAccount(final String account) throws InvalidAccountException {
        if(account == null) {
            throw new InvalidAccountException();
        }
        final Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(account).matches()) {
            throw new InvalidAccountException();
        }
        if(account.length() > 8) {
            throw new InvalidAccountException();
        }
    }

    public String getAccountType() {
        return accountType;
    }
}

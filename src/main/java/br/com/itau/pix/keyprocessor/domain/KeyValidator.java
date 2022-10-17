package br.com.itau.pix.keyprocessor.domain;

import br.com.itau.pix.keyprocessor.domain.exception.*;
import org.apache.commons.lang3.NotImplementedException;
import java.util.regex.Pattern;

public enum KeyValidator {
    ;

    private static final String CELLPHONE_TYPE = "celular";
    private static final String EMAIL_TYPE = "email";
    private static final String CPF_TYPE = "cpf";
    private static final String CNPJ_TYPE = "cnpj";
    private static final String ALEATORY_TYPE = "aleatorio";
    private String type;

    KeyValidator(String type) {
        this.type = type;
    }

    public static void isValid(final String type, final String key) throws Exception{
        switch (type){
            case CELLPHONE_TYPE:
                throw new NotImplementedException();
            case ALEATORY_TYPE:
                verifyIfIsValidAleatoryKey(key);
                break;
            case CPF_TYPE:
                verifyIfIsValidCpf(key);
                break;
            case EMAIL_TYPE:
                verifyIfIsValidEmail(key);
                break;
            case CNPJ_TYPE:
                verifyIfIsValidCnpj(key);
                break;
            default:
                throw new InvalidKeyTypeException();
        }

    }

    public static void isTelefoneValido() {

    }

    public static void beginWithPlusSymbol(final String phoneNumber) throws InvalidPhoneNumberException {
        if(!phoneNumber.startsWith("+")) {
            throw new InvalidPhoneNumberException();
        }
    }

    public static void verifyIsHasLetters(final String phoneNumber) throws InvalidPhoneNumberException {
        final Pattern pattern = Pattern.compile("^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$");
        if(!pattern.matcher(phoneNumber).matches()) {
            throw new InvalidPhoneNumberException();
        }
    }

    public static void verifyIfIsValidEmail(final String email) throws InvalidEmailException {
        if(email == null) {
            throw new InvalidEmailException();
        }
        final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(!pattern.matcher(email).matches()) {
            throw new InvalidEmailException();
        }
        if(email.length() > 77) {
            throw new InvalidEmailException();
        }
    }

    public static void verifyIfIsValidAleatoryKey(final String aleatoryKey) throws InvalidAleatoryKeyException {
        if(aleatoryKey == null) {
            throw new InvalidAleatoryKeyException();
        }
        final Pattern pattern = Pattern.compile("^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$", Pattern.CASE_INSENSITIVE);
        if(!pattern.matcher(aleatoryKey).matches()) {
            throw new InvalidAleatoryKeyException();
        }
        if(aleatoryKey.length() > 36) {
            throw new InvalidAleatoryKeyException();
        }
    }

    public static void verifyIfIsValidCpf(final String cpf) throws InvalidCpfException {
        if(cpf == null) {
            throw new InvalidCpfException();
        }
        final Pattern pattern = Pattern.compile("^[0-9]{11}");
        if(!pattern.matcher(cpf).matches()) {
            throw new InvalidCpfException();
        }
        isCpfValid(cpf);
    }

    public static void verifyIfIsValidCnpj(final String cnpj) throws InvalidCnpjException {
        if(cnpj == null) {
            throw new InvalidCnpjException();
        }
        final Pattern pattern = Pattern.compile("^[0-9]{14}");
        if(!pattern.matcher(cnpj).matches()) {
            throw new InvalidCnpjException();
        }
        isCnpjValid(cnpj);
    }

    public static void isCpfValid(final String cpf) throws InvalidCpfException {

        int total = 0;

        total += Integer.valueOf(cpf.substring(0, 1)) * 10;
        total += Integer.valueOf(cpf.substring(1, 2)) * 9;
        total += Integer.valueOf(cpf.substring(2, 3)) * 8;
        total += Integer.valueOf(cpf.substring(3, 4)) * 7;
        total += Integer.valueOf(cpf.substring(4, 5)) * 6;
        total += Integer.valueOf(cpf.substring(5, 6)) * 5;
        total += Integer.valueOf(cpf.substring(6, 7)) * 4;
        total += Integer.valueOf(cpf.substring(7, 8)) * 3;
        total += Integer.valueOf(cpf.substring(8, 9)) * 2;

        int digito1 = (total*10)%11;

        total = 0;

        total += Integer.valueOf(cpf.substring(0,1)) * 11;
        total += Integer.valueOf(cpf.substring(1,2)) * 10;
        total += Integer.valueOf(cpf.substring(2,3)) * 9;
        total += Integer.valueOf(cpf.substring(3,4)) * 8;
        total += Integer.valueOf(cpf.substring(4,5)) * 7;
        total += Integer.valueOf(cpf.substring(5,6)) * 6;
        total += Integer.valueOf(cpf.substring(6,7)) * 5;
        total += Integer.valueOf(cpf.substring(7,8)) * 4;
        total += Integer.valueOf(cpf.substring(8,9)) * 3;
        total += Integer.valueOf(cpf.substring(9,10)) * 2;

        int digito2 = (total*10)%11;

        if(!String.valueOf(digito1).equals(cpf.substring(9)) && !String.valueOf(digito2).equals(cpf.substring(10))){
            throw new InvalidCpfException();
        }

    }

    public static void isCnpjValid(final String cnpj) throws InvalidCnpjException {

        int total = 0;

        total += Integer.valueOf(cnpj.substring(0, 1)) * 5;
        total += Integer.valueOf(cnpj.substring(1, 2)) * 4;
        total += Integer.valueOf(cnpj.substring(2, 3)) * 3;
        total += Integer.valueOf(cnpj.substring(3, 4)) * 2;
        total += Integer.valueOf(cnpj.substring(4, 5)) * 9;
        total += Integer.valueOf(cnpj.substring(5, 6)) * 8;
        total += Integer.valueOf(cnpj.substring(6, 7)) * 7;
        total += Integer.valueOf(cnpj.substring(7, 8)) * 6;
        total += Integer.valueOf(cnpj.substring(8, 9)) * 5;
        total += Integer.valueOf(cnpj.substring(9, 10)) * 4;
        total += Integer.valueOf(cnpj.substring(10, 11)) * 3;
        total += Integer.valueOf(cnpj.substring(11, 12)) * 2;

        int rest = total%11;
        int digito1;

        if(rest == 0 || rest == 1) {
            digito1 = 0;
        } else {
            digito1 = 11 - rest;
        }

        total = 0;

        total += Integer.valueOf(cnpj.substring(0, 1)) * 6;
        total += Integer.valueOf(cnpj.substring(1, 2)) * 5;
        total += Integer.valueOf(cnpj.substring(2, 3)) * 4;
        total += Integer.valueOf(cnpj.substring(3, 4)) * 3;
        total += Integer.valueOf(cnpj.substring(4, 5)) * 2;
        total += Integer.valueOf(cnpj.substring(5, 6)) * 9;
        total += Integer.valueOf(cnpj.substring(6, 7)) * 8;
        total += Integer.valueOf(cnpj.substring(7, 8)) * 7;
        total += Integer.valueOf(cnpj.substring(8, 9)) * 6;
        total += Integer.valueOf(cnpj.substring(9, 10)) * 5;
        total += Integer.valueOf(cnpj.substring(10, 11)) * 4;
        total += Integer.valueOf(cnpj.substring(11, 12)) * 3;
        total += Integer.valueOf(cnpj.substring(12, 13)) * 2;

        int rest2 = total%11;
        int digito2;

        if(rest2 == 0 || rest2 == 1) {
            digito2 = 0;
        } else {
            digito2 = 11 - rest2;
        }

        if(!String.valueOf(digito1).equals(cnpj.substring(12)) && !String.valueOf(digito2).equals(cnpj.substring(13))){
            throw new InvalidCnpjException();
        }

    }

}

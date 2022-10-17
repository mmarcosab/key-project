package br.com.itau.pix.keyprocessor.domain.exception;

import br.com.itau.pix.keyprocessor.domain.AccountValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountValidatorTests {

    @Test
    public void givenANullAccountNumberWithLettersWhenValidateThenThrowAnException() {
        assertThrows(InvalidAccountException.class, ()-> AccountValidator.verifyIfIsValidAccount(null));
    }

    @Test
    public void givenAnAccountNumberWithLettersWhenValidateThenThrowAnException() {
        assertThrows(InvalidAccountException.class, ()-> AccountValidator.verifyIfIsValidAccount("111AA34"));
    }

    @Test
    public void givenAnAccountWithInvalidLengthWhenValidateThenThrowAnException() {
        assertThrows(InvalidAccountException.class, ()-> AccountValidator.verifyIfIsValidAccount("111222333"));
    }

    @Test
    public void givenAValidAccountNumberWhenValidateThenWillNotThrowAnException() throws InvalidPhoneNumberException {
        assertDoesNotThrow(() -> AccountValidator.verifyIfIsValidAccount("11122233"));
    }

    @Test
    public void givenANullAgencyNumberWithLettersWhenValidateThenThrowAnException() {
        assertThrows(InvalidAgencyException.class, ()-> AccountValidator.verifyIfIsValidAgency(null));
    }

    @Test
    public void givenAnAgencyNumberWithLettersWhenValidateThenThrowAnException() {
        assertThrows(InvalidAgencyException.class, ()-> AccountValidator.verifyIfIsValidAgency("11AA"));
    }

    @Test
    public void givenAnAgencyWithInvalidLengthWhenValidateThenThrowAnException() {
        assertThrows(InvalidAgencyException.class, ()-> AccountValidator.verifyIfIsValidAgency("11122"));
    }

    @Test
    public void givenAValidAgencyNumberWhenValidateThenWillNotThrowAnException() throws InvalidPhoneNumberException {
        assertDoesNotThrow(() -> AccountValidator.verifyIfIsValidAgency("1112"));
    }

}

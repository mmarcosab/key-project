package br.com.itau.pix.keyprocessor.domain.exception;

import br.com.itau.pix.keyprocessor.domain.KeyValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyValidatorTests {

    @Test
    public void givenAPhoneNumberWithoutPlusSymbolWhenValidateThenThrowAnException() {
        assertThrows(InvalidPhoneNumberException.class, () -> KeyValidator.beginWithPlusSymbol("1122123456789"));
    }

    @Test
    public void givenAPhoneNumberWithPlusSymbolWhenValidateThenWillNotThrowAnException() throws InvalidPhoneNumberException {
        assertDoesNotThrow(() -> KeyValidator.beginWithPlusSymbol("+1122123456789"));
    }

    @Test
    public void givenAValidPhoneNumberWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIsHasLetters("+5522923456789"));
    }

    @Test
    public void givenAnInvalidPhoneNumberWithoutPlusSymbolWhenValidateThenThrowAnException() {
        assertThrows(InvalidPhoneNumberException.class, () -> KeyValidator.verifyIsHasLetters("11221A3456789"));
    }

    @Test
    public void givenAnValidEmailFinishedWithDotComWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidEmail("teste@teste.com"));
    }

    @Test
    public void givenAnNullEmailFinishedWithDotComWhenValidateThenThrowAnException() {
        assertThrows(InvalidEmailException.class, ()-> KeyValidator.verifyIfIsValidEmail(null));
    }

    @Test
    public void givenAnValidEmailFinishedWithDotComInCapsWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidEmail("TESTE@TESTE.COM"));
    }

    @Test
    public void givenAnValidEmailFinishedWithDotBrInCapsWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidEmail("TESTE@TESTE.COM.BR"));
    }

    @Test
    public void givenAnInvalidEmailWIthInvalidLengthWhenValidateThenWillNotThroAnException() {
        assertThrows(InvalidEmailException.class, ()-> KeyValidator.verifyIfIsValidEmail("TTTESTETESTE-TESTETESTE-TESTETESTETESTETESTE-TESTETESTE_TESTETESTE@TESTE.COM.BR"));
    }

    @Test
    public void givenAnStringThatIsNotAnEmailWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidEmailException.class, ()-> KeyValidator.verifyIfIsValidEmail("teste"));
    }
    //Validate uuid versions 3, 4 and 5
    @Test
    public void givenAnValidAleatoryKeyWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidAleatoryKey("20354d7a-e4fe-47af-8ff6-187bca92f3f9"));
    }

    @Test
    public void givenANullAleatoryKeyWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidAleatoryKeyException.class, () -> KeyValidator.verifyIfIsValidAleatoryKey(null));
    }


    @Test
    public void givenAnValidAleatoryKeyWithCapsWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidAleatoryKey("C73BCDCC-2669-4Bf6-81d3-E4AE73FB11FD"));
    }

    @Test
    public void givenAnValidAleatoryKeyWithInvalidLengthWhenValidateThenThrowAnException() {
        assertThrows(InvalidAleatoryKeyException.class, () -> KeyValidator.verifyIfIsValidAleatoryKey("C73BCDCC-2669-4Bf6-81d3-E4AE73FB11FDTT"));
    }

    @Test
    public void givenAnInvalidAleatoryKeyWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidAleatoryKeyException.class, () -> KeyValidator.verifyIfIsValidAleatoryKey("s69d.6cb-0022-41ae-aa4c-2799fa6c22e2"));
    }

    @Test
    public void givenAnInvalidAleatoryWithoutTraceKeyWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidAleatoryKeyException.class, () -> KeyValidator.verifyIfIsValidAleatoryKey("c73bcdcc26694bf681d3e4ae73fb11fd"));
    }

    @Test
    public void givenAnValidCpfWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidCpf("11330678087"));
    }

    @Test
    public void givenANullCpfWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCpfException.class, () -> KeyValidator.verifyIfIsValidCpf(null));
    }

    @Test
    public void givenAnInvalidCpfWithLettersAndSpecialCharactersWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCpfException.class, () -> KeyValidator.verifyIfIsValidCpf("111A2A33344"));
        assertThrows(InvalidCpfException.class, () -> KeyValidator.verifyIfIsValidCpf("111.222.333-44"));
    }

    @Test
    public void givenAnInvalidCpfWithInvalidLengthCharactersWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCpfException.class, () -> KeyValidator.verifyIfIsValidCpf("1112223334423"));
        assertThrows(InvalidCpfException.class, () -> KeyValidator.verifyIfIsValidCpf("1112223334"));
    }

    @Test
    public void givenAnValidCnpjWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow(() -> KeyValidator.verifyIfIsValidCnpj("93910138000109"));
    }

    @Test
    public void givenANullCnpjWithLettersAndSpecialCharactersWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCnpjException.class, () -> KeyValidator.verifyIfIsValidCnpj(null));
    }

    @Test
    public void givenAnInvalidCnpjWithLettersAndSpecialCharactersWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCnpjException.class, () -> KeyValidator.verifyIfIsValidCnpj("111A2A33344444"));
        assertThrows(InvalidCnpjException.class, () -> KeyValidator.verifyIfIsValidCnpj("11.222.333/4444-55"));
    }

    @Test
    public void givenAValidCnpjWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow( () -> KeyValidator.isCnpjValid("03976516000164"));
    }

    @Test
    public void givenAValidCpfWhenValidateThenWillNotThrowAnException() {
        assertDoesNotThrow( () -> KeyValidator.isCpfValid("78152893048"));
    }

    @Test
    public void givenAnInvalidCnpjWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCnpjException.class, () -> KeyValidator.isCnpjValid("03976516000162"));
    }

    @Test
    public void givenAnInvalidCpfWhenValidateThenWillNotThrowAnException() {
        assertThrows(InvalidCpfException.class, () -> KeyValidator.isCpfValid("30016370054"));
    }
}

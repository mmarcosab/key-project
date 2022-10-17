package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidPhoneNumberException  extends Exception{
    public InvalidPhoneNumberException(){
        super("Invalid phone number");
    }
}

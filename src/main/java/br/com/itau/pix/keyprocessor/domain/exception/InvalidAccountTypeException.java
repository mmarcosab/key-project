package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidAccountTypeException extends Exception{
    public InvalidAccountTypeException(){
        super("Invalid account type");
    }
}

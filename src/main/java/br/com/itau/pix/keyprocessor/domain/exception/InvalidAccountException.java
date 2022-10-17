package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidAccountException extends Exception{
    public InvalidAccountException(){
        super("Invalid account");
    }
}

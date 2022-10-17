package br.com.itau.pix.keyprocessor.domain.exception;

public class KeyAlreadyInactiveException extends Exception{
    public KeyAlreadyInactiveException(){
        super("Key already inactive");
    }
}

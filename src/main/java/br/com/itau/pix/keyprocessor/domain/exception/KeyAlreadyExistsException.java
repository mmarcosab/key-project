package br.com.itau.pix.keyprocessor.domain.exception;

public class KeyAlreadyExistsException extends Exception{
    public KeyAlreadyExistsException(){
        super("Key already exists");
    }
}

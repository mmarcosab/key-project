package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidKeyTypeException extends Exception{
    public InvalidKeyTypeException(){
        super("Key type is invalid");
    }
}

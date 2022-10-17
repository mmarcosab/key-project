package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidAleatoryKeyException extends Exception{
    public InvalidAleatoryKeyException(){
        super("Invalid aleatory key");
    }
}

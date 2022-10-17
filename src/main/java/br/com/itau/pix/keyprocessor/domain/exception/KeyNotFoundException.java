package br.com.itau.pix.keyprocessor.domain.exception;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(){
        super("Key not found");
    }
}

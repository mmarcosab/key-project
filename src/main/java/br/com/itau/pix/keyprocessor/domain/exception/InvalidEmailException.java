package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super("Invalid email");
    }
}

package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidAgencyException extends Exception{
    public InvalidAgencyException(){
        super("Invalid agency");
    }
}

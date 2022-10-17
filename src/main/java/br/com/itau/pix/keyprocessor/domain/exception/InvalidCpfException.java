package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidCpfException extends Exception{
    public InvalidCpfException(){
        super("Invalid cpf");
    }
}

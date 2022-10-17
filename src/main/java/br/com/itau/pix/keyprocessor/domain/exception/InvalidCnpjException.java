package br.com.itau.pix.keyprocessor.domain.exception;

public class InvalidCnpjException extends Exception{
    public InvalidCnpjException(){
        super("Invalid cnpj");
    }
}

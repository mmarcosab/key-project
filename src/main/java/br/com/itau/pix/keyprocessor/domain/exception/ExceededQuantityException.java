package br.com.itau.pix.keyprocessor.domain.exception;

public class ExceededQuantityException extends Exception{
    public ExceededQuantityException(){
        super("Exceeded quantity");
    }
}

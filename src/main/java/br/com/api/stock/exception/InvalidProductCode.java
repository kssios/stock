package br.com.api.stock.exception;

public class InvalidProductCode extends ApplicationException {

    private static final String ERROR_MESSAGE = "Código do produto não informado ou inválido!";

    public InvalidProductCode() {
        super(ERROR_MESSAGE);
    }
}

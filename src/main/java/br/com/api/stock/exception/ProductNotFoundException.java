package br.com.api.stock.exception;

public class ProductNotFoundException extends ApplicationException {

    private static final String ERROR_MESSAGE = "Produto não encontrado!";

    public ProductNotFoundException() {
        super(ERROR_MESSAGE);
    }
}

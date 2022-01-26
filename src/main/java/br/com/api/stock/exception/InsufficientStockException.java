package br.com.api.stock.exception;

public class InsufficientStockException extends ApplicationException {

    private static final String ERROR_MESSAGE = "Saldo em estoque insuficiente!";

    public InsufficientStockException() {
        super(ERROR_MESSAGE);
    }
}

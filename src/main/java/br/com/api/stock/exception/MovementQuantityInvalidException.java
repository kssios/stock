package br.com.api.stock.exception;

public class MovementQuantityInvalidException extends ApplicationException {

    private static final String ERROR_MESSAGE = "Quantidade informada inválida!";

    public MovementQuantityInvalidException() {
        super(ERROR_MESSAGE);
    }
}

package br.com.api.stock.exception;

public class OperationTypeNotFoundException extends ApplicationException {

    private static final String ERROR_MESSAGE = "Tipo de operação inválido!";

    public OperationTypeNotFoundException() {
        super(ERROR_MESSAGE);
    }

}

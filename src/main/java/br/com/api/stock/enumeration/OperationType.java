package br.com.api.stock.enumeration;

import br.com.api.stock.exception.OperationTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {

    COMPRA(1),
    VENDA(2);

    private final int id;

    public static OperationType findById(final int id) {
        switch (id) {
            case 1:
                return COMPRA;
            case 2:
                return VENDA;
            default:
                throw new OperationTypeNotFoundException();
        }
    }
}

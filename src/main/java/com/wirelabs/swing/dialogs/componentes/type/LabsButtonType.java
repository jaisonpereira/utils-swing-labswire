package com.wirelabs.swing.dialogs.componentes.type;

/**
 *
 * @author jpereira
 */
public enum LabsButtonType {

    NOVO(1), ALTERAR(2), EXCLUIR(3), PESQUISAR(4), SALVAR(5), CANCELAR(6);

    private final int value;

    LabsButtonType(int valor) {
        this.value = valor;
    }

    public int getValue() {
        return value;
    }

}

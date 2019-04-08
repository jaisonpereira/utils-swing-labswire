/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

/**
 *
 * @author jpereira
 */
public class ColunsLabs {

    private Integer size;
    private String descricao;
    private boolean hide;

    public ColunsLabs(Integer size, String descricao) {
        this.size = size;
        this.descricao = descricao;
        this.hide = false;
    }

    public ColunsLabs(Integer size, String descricao, boolean hide) {
        this.size = size;
        this.descricao = descricao;
        this.hide = hide;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

}

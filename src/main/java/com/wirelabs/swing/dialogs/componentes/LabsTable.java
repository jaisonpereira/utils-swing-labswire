/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import javax.swing.JTable;

/**
 *
 * @author jpereira Jtable encapsulada
 */
public class LabsTable extends JTable {

    public LabsTable() {
        initialize();
    }

    private void initialize() {
        this.setGridColor(LabsColor.getWhiteColor());
        this.setBackground(LabsColor.getWhiteColor());
        this.setForeground(LabsColor.getBlackColor());
        this.setSelectionBackground(LabsColor.getSuccessColor());
        this.setSelectionForeground(LabsColor.getBlackColor());
    }
}

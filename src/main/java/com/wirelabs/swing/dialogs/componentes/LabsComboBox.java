/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import javax.swing.JComboBox;

/**
 *
 * @author jpereira
 */
public class LabsComboBox<Object> extends JComboBox<Object> {

	public LabsComboBox() {
		this.setBorder(null);
		this.setBackground(LabsColor.getWhiteColor());
	}

}

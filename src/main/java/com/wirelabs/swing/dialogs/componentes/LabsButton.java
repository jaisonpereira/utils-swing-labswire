/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import com.wirelabs.common.utils.StringManager;
import com.wirelabs.swing.dialogs.componentes.type.LabsButtonType;

/**
 *
 * @author jpereira
 */
public class LabsButton extends JButton {

	private void initialize() {
		this.setFont(new java.awt.Font("Segoe UI", 0, 14));
		this.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		this.setBackground(Color.white);
		// set enter default
		this.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				acaoKeyPressed(evt);
			}
		});
	}

	public LabsButton() {
		initialize();
	}

	private void setTypeButton(LabsButtonType type) {
		if (type.equals(LabsButtonType.NOVO)) {
			getDefaultColor();
			this.setText("Novo");
			this.setMnemonic('N');
			this.setToolTipText("ALT+N  \n Adicionar");
		} else if (type.equals(LabsButtonType.SALVAR)) {
			getDefaultColor();
			this.setText("Salvar");
			this.setMnemonic('S');
			this.setToolTipText("ALT+S  \n Salva alterações realizadas");
		} else if (type.equals(LabsButtonType.ALTERAR)) {
			getDefaultColor();
			this.setText("Alterar");
		} else if (type.equals(LabsButtonType.PESQUISAR)) {
			getDefaultColor();
			this.setText("Pesquisar");
			this.setToolTipText("ALT+Q  \n Pesquisar ");
			this.setMnemonic('q');
		} else if (type.equals(LabsButtonType.CANCELAR)) {
			getDefaultColor();
			this.setText("Cancelar");
			this.setToolTipText("ALT+C  \n Cancelar operação");
			this.setMnemonic('C');
		} else if (type.equals(LabsButtonType.EXCLUIR)) {
			getDefaultColor();
			this.setText("Excluir");
		}
	}

	/**
	 * Impede Substituicao de texto apos atribuido
	 *
	 * @param text
	 */
	@Override
	public void setText(String text) {
		if (StringManager.get().isNullOrTrimEmpty(this.getText())) {
			super.setText(text); // To change body of generated methods, choose Tools | Templates.
		}
	}

	public LabsButton(LabsButtonType type) {
		initialize();
		setTypeButton(type);
	}

	public LabsButton isPrimary() {
		getPrimaryColor();
		return this;
	}

	@Override
	public ActionListener[] getActionListeners() {
		return super.getActionListeners(); // To change body of generated methods, choose Tools | Templates.
	}

	protected void acaoKeyPressed(java.awt.event.KeyEvent evt) {
		ActionListener[] actions = getActionListeners();
		if (actions.length > 0 && evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            JOptionPane.showMessageDialog(null, "teste ");
			actions[0].actionPerformed(null);
		}
	}

	private void getPrimaryColor() {
		this.setForeground(LabsColor.getWhiteColor());
		this.setBackground(LabsColor.getPrimaryColor());
	}

	private void getDefaultColor() {
		this.setForeground(LabsColor.getBlackColor());
		this.setBackground(LabsColor.getWhiteColor());
	}

	private void getSuccessColor() {
		this.setForeground(LabsColor.getWhiteColor());
		this.setBackground(LabsColor.getSuccessColor());
	}

	public LabsButton(String color) {
		initialize();
		if (color.equals("green")) {
			getSuccessColor();
		}
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

import java.lang.reflect.Field;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.wirelabs.common.utils.StringManager;
import com.wirelabs.swing.dialogs.LabsMessageDialog;

/**
 * Classe controla dialogs do sistema
 *
 * @author jpereira
 */
public abstract class LabsDialog extends JDialog implements BaseWindowController {

	private Object dialog;
	private final String title = "Labs Wire Software System";

	public LabsDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
	}

	@Override
	public void closeAndCleanFields() {
		clearFields();
		this.dispose();
	}

	public LabsDialog(String title) {
		super();
		this.initiliaze(title);
	}

	public LabsDialog() {
		super();
		this.initiliaze(null);
	}

	@Override
	public void setTargetWindow(Object dialog) {
		this.dialog = dialog;
	}

	private void initiliaze(String titleArgument) {
		if (!StringManager.get().isNullOrEmpty(titleArgument)) {
			this.setTitle(titleArgument);
		} else {
			this.setTitle(this.title);
		}
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		setLayout(new java.awt.CardLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void clearFields() {
		// limpa filtros
		try {
			if (dialog == null) {
				LabsMessageDialog.error("Dialog nao definida para clear ,\n definir no construtor da classe");
				return;
			}
			for (final Field field : dialog.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (JTextField.class.isAssignableFrom(field.getType())) {
					final JTextField text = (JTextField) field.get(this);
					if (text != null) {
						text.setText("");
					}
				}
				// text area
				if (JTextArea.class.isAssignableFrom(field.getType())) {
					final JTextArea text = (JTextArea) field.get(this);
					if (text != null) {
						text.setText("");
					}
				}
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			LabsMessageDialog.error(ex.getMessage());
		}
	}

	@Override
	public boolean hasFieldValue() {
		// limpa filtros
		try {
			if (dialog == null) {
				throw new Exception("Dialog nao definida para clear ,\n definir no construtor da classe");
			}
			for (final Field field : dialog.getClass().getDeclaredFields()) {
				if (JTextField.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final JTextField text = (JTextField) field.get(this);
					if (!StringManager.get().isNullOrTrimEmpty(text.getText())) {
						return true;
					}
				}
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			LabsMessageDialog.error(ex.getMessage());
		}
		return false;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

import java.awt.Color;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.wirelabs.common.utils.StringManager;
import com.wirelabs.swing.dialogs.LabsMessageDialog;

/**
 *
 * @author jpereira
 */
public abstract class LabsFrame extends JFrame implements BaseWindowController {

	private static final long serialVersionUID = 1405201091096745860L;

	protected Object frame;

	private final String title = "Labs Wire Software System";

	public LabsFrame() {
		initiliaze(null);
	}

	public LabsFrame(String title) {
		initiliaze(title);
	}

	@Override
	public void clearFields() {
		try {
			if (frame == null) {
				LabsMessageDialog.error(DIALOG_UNDEFINED);
				return;
			}
			for (final Field field : frame.getClass().getDeclaredFields()) {
				if (JTextField.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final JTextField text = (JTextField) field.get(this);
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
	public void setTargetWindow(Object frame) {
		this.frame = frame;
	}

	@Override
	public void closeAndCleanFields() {
		clearFields();
		this.dispose();
	}

	private void initiliaze(String titleArgument) {
		if (!StringManager.get().isNullOrEmpty(titleArgument)) {
			this.setTitle(titleArgument);
		} else {
			this.setTitle(this.title);
		}
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		this.getContentPane().setBackground(Color.WHITE);
		setLayout(new java.awt.CardLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.Document;

/**
 *
 * @author jpereira
 */
public class LabsPasswordField extends JPasswordField {

	private static final long serialVersionUID = -4970037347318603815L;

	public static void main(final String[] args) {
		final LabsPasswordField tf = new LabsPasswordField("");
		tf.setColumns(20);
		tf.setPlaceholder("All your base are belong to us!");
		final Font f = tf.getFont();
		tf.setFont(new Font(f.getName(), f.getStyle(), 30));
		JOptionPane.showMessageDialog(null, tf);
	}

	protected String placeholder;

	public LabsPasswordField(final Document pDoc, final String pText, final int pColumns) {
		super(pDoc, pText, pColumns);
		initiliaze();
	}

	public LabsPasswordField(final int pColumns) {
		super(pColumns);
		initiliaze();
	}

	public LabsPasswordField(final String placeHolder) {
		initiliaze();
		setPlaceholder(placeHolder);

	}

	public LabsPasswordField() {
		initiliaze();
		setPlaceholder("");

	}

	private void initiliaze() {
		setBorder(null);
	}

	public LabsPasswordField(final String pText, final int pColumns) {
		super(pText, pColumns);
		initiliaze();
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(LabsColor.getSuccessColor());
		g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

}

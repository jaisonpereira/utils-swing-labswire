/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.wirelabs.common.utils.StringManager;

/**
 * Campo para a inserção de números com base em um formato.
 *
 */
public class LabsNumberFormatField extends LabsTextField {

	private static final long serialVersionUID = -7506506392528621022L;
	private static final NumberFormat MONETARY_FORMAT = new DecimalFormat("R$ #,##0.00");
	private static final NumberFormat DEFAULT_FORMAT = new DecimalFormat("0.00");
	private static final NumberFormat ONLY_DIGITS = new DecimalFormat("00");
	private NumberFormat numberFormat;
	private int limit = -1;

	public LabsNumberFormatField(int casasDecimais) {
		setNumberFormatInit(new DecimalFormat((casasDecimais == 0 ? "#,##0" : "#,##0.") + makeZeros(casasDecimais)));
	}

	public LabsNumberFormatField(String placeholder) {
		super(placeholder);
		setNumberFormatInit(DEFAULT_FORMAT);
		setPlaceholder(placeholder);
	}

	public LabsNumberFormatField isOnlyDigits() {
		setNumberFormatInit(ONLY_DIGITS);
		return this;
	}

	public LabsNumberFormatField isRealMonetary() {
		setNumberFormatInit(MONETARY_FORMAT);
		return this;
	}

	public void setNumberFormatInit(NumberFormat format) {// define o formato do
		// número
		numberFormat = format;// alinhamento horizontal para o texto
		setHorizontalAlignment(RIGHT);// documento responsável pela formatação
		// do campo
		setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				String text = new StringBuilder(LabsNumberFormatField.this.getText().replaceAll("[^0-9]", ""))
						.append(str.replaceAll("[^0-9]", "")).toString();
				super.remove(0, getLength());
				if (!StringManager.get().isNullOrTrimEmpty(text)) {
					text = new BigInteger(text).toString();
					super.insertString(0, numberFormat.format(new BigDecimal(
							getLimit() > 0 == text.length() > getLimit() ? text.substring(0, getLimit()) : text)
									.divide(new BigDecimal(Math.pow(10, numberFormat.getMaximumFractionDigits())))),
							a);
				} else {
					setPlaceholder(placeholder);
				}
			}

			@Override
			public void remove(int offs, int len) throws BadLocationException {
				super.remove(offs, len);
				if (len != getLength()) {
					setPlaceholder(placeholder);
				}
			}
		});// mantem o cursor no final
			// do campo
		addCaretListener(new CaretListener() {
			boolean update = false;

			@Override
			public void caretUpdate(CaretEvent e) {
				if (!update) {
					update = true;
					setCaretPosition(getText().length());
					update = false;
				}
			}
		});// limpa o campo se
			// apertar DELETE
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					setText("");
				}
			}
		});// formato
			// inicial
//        setText("0");
		setCaretPosition(getText().length());
	}

	/**
	 * * Define um valor BigDecimal ao campo**
	 *
	 * @param value
	 */
	public void setValue(BigDecimal value) {
		super.setText(numberFormat.format(value));
	}

	/**
	 * * Recupera um valor BigDecimal do campo**
	 *
	 * @return
	 */
	public final BigDecimal getValue() {
		return new BigDecimal(getText().replaceAll("[^0-9]", ""))
				.divide(new BigDecimal(Math.pow(10, numberFormat.getMaximumFractionDigits())));
	}

	/**
	 * * Recupera o formatador atual do campo**
	 *
	 * @return
	 */
	public NumberFormat getNumberFormat() {
		return numberFormat;
	}

	/**
	 * * Define o formatador do campo** @param numberFormat
	 */
	public void setNumberFormat(NumberFormat numberFormat) {
		this.numberFormat = numberFormat;
	}

	/**
	 * * Preenche um StringBuilder com zeros** @param zeros*
	 *
	 * @return
	 */
	private static final String makeZeros(int zeros) {
		if (zeros >= 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < zeros; i++) {
				builder.append('0');
			}
			return builder.toString();
		} else {
			throw new RuntimeException("Número de casas decimais inválida (" + zeros + ")");
		}
	}

	/**
	 * * Recupera o limite do campo.** @return
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * * Define o limite do campo, limit < 0 para deixar livre (default) Ignora os
	 * pontos e virgulas do formato, conta* somente com os números** @param limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	// testes, pode ser removido
	public static void main(String[] args) {
		JFrame frame = new JFrame("Teste do campo");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new LabsNumberFormatField("teste") {
			{// limita a 6
				// caracteres
				setLimit(6);
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

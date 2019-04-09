/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.wirelabs.common.utils.DateUtil;
import com.wirelabs.common.utils.StringManager;
import com.wirelabs.swing.dialogs.componentes.LabsNumberFormatField;

/**
 *
 * @author jpereira Util for getValue field swing
 */
public interface FieldController {

	default Object getField(JComboBox<?> combo) throws Exception {
		try {
			if (combo.getSelectedItem() == null) {
				return null;
			}
			return combo.getSelectedItem();
		} catch (Exception e) {
			throw new Exception("Error on getField from  jcombo ", e);
		}
	}

	default String getField(JTextArea field) throws Exception {
		try {
			if (StringManager.get().isNullOrTrimEmpty(field.getText())) {
				return null;
			}
			return field.getText();
		} catch (Exception e) {
			throw new Exception("error in get String from textArea ", e);
		}
	}

	default String getField(JTextField field) throws Exception {
		try {
			if (StringManager.get().isNullOrTrimEmpty(field.getText())) {
				return null;
			}
			return field.getText();
		} catch (Exception e) {
			throw new Exception("error on get String from textField ", e);
		}
	}

	default BigDecimal getBigDecimalField(JTextField field) throws Exception {
		try {
			if (StringManager.get().isNullOrTrimEmpty(field.getText())) {
				return null;
			}
			return ((LabsNumberFormatField) field).getValue();
		} catch (Exception e) {
			throw new Exception("error on get bigDecimalValue ", e);
		}
	}

	default Short getShortField(JTextField field) throws Exception {
		try {
			if (StringManager.get().isNullOrTrimEmpty(field.getText())) {
				return null;
			}
			return Short.valueOf(field.getText());
		} catch (Exception e) {
			throw new Exception("error on get Short value from textfield ", e);
		}
	}

	default Integer getIntegerField(JTextField field) throws Exception {
		try {
			if (StringManager.get().isNullOrTrimEmpty(field.getText())) {
				return null;
			}
			return Integer.valueOf(field.getText());
		} catch (Exception e) {
			throw new Exception("erro ao capturar Integer field ", e);
		}
	}

	default void setField(JTextField field, Object value) {
		if (value == null) {
			field.setText("");
			return;
		}
		if (field instanceof LabsNumberFormatField) {
			((LabsNumberFormatField) field).setText((value.toString()));
		} else if (value instanceof String) {
			field.setText("" + value.toString());
		} else if (value instanceof LocalDateTime) {
			field.setText(DateUtil.get().toStringDateTime((LocalDateTime) value));
		} else {
			field.setText("" + value.toString());
		}
	}

	default void setTextArea(JTextArea area, Object value) {
		if (value == null) {
			area.setText("");
			return;
		} else {
			area.setText(value.toString());
		}
	}

}

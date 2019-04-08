/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.wirelabs.common.utils.StringManager;
import com.wirelabs.swing.dialogs.LabsMessageDialog;

/**
 *
 * @author jpereira
 *
 *         frame abstrato para search
 */
public abstract class LabsFrameSearchCrud<T> extends LabsFrame {

	protected T model;

	protected abstract void startTable();

	protected abstract void start() throws Exception;

	/**
	 * Metodo define propriedades para inicilizacao deve ser colocado no construtor
	 *
	 * @param dialog
	 * @param panel
	 */
	protected void setPropertiesDialog(Object dialog, JPanel panel) {
		setTargetWindow(this);
		setActionPanelShortCut(panel);
	}

	/**
	 * Metodo starta janela
	 *
	 * @param entity
	 */
	public void initialize(T entity) {
		if (entity == null) {
			setLocationRelativeTo(null);
		}
		this.model = entity;
		this.setVisible(true);
	}

	@Override
	public boolean hasFieldValue() {
		// limpa filtros
		try {
			if (frame == null) {
				throw new Exception("frame nao definida para clear ,\n definir no construtor da classe");
			}
			for (final Field field : frame.getClass().getDeclaredFields()) {
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

	@Override
	public void executeCancel() {
		if (LabsMessageDialog.confirmDialog("Deseja realmente cancelar ? todas as alterações serão perdidas",
				"Cancelar Edição")) {
			closeAndCleanFields();
			this.model = null;
		}
	}

	protected ShortCutKey cancelar = new ShortCutKey(2);

	/**
	 * Classe aninhada que define shortcut
	 */
	protected class ShortCutKey extends AbstractAction {

		int opcao;

		public ShortCutKey(int opcao) {
			this.opcao = opcao;
		}

		public void actionPerformed(ActionEvent e) {
			switch (opcao) {
			case 1:
				break;
			case 2:
				executeCancel();
				break;
			default:
			}
		}
	}

	protected void setActionPanelShortCut(JPanel painel) {
		// Damos um nome para cada ação. Esse nome é útil pois mais de
		// uma tecla pode ser associada a cada ação, como veremos abaixo
		ActionMap actionMap = painel.getActionMap();
		actionMap.put("cancelar", cancelar);
		painel.setActionMap(actionMap);
		InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		imap = setDefaultActions(imap);
	}

	protected InputMap setDefaultActions(InputMap imap) {
//        imap.put(KeyStroke.getKeyStroke("F1"), "botao1");
		// salvar
//        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "salvar");// salvar
		// cancelar
		imap.put(KeyStroke.getKeyStroke("ESCAPE"), "cancelar");// sai da tela
		return imap;
	}

}

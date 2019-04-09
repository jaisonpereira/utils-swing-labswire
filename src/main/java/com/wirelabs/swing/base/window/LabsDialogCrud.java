/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.wirelabs.swing.dialogs.LabsMessageDialog;

/**
 *
 * @author jpereira
 * @param <T>
 */
public abstract class LabsDialogCrud<T extends Object> extends LabsDialog implements FieldController {

	private static final long serialVersionUID = -2529098664622631884L;

	protected T model;

	protected AccessibleWindow accesible;

	protected abstract T getModelFromFields() throws Exception;

	protected abstract void setModelFromFields() throws Exception;

	protected abstract void save() throws Exception;

	protected abstract void start();

	/**
	 * Method defines values for contructor
	 *
	 * @param dialog
	 * @param panel
	 */
	protected void setPropertiesDialog(Object dialog, JPanel panel) {
		setTargetWindow(this);
		setActionPanelShortCut(panel);
	}

	/**
	 *
	 * @param entity parametro seta model caso exista
	 * @param window janela de comunicação
	 * @throws java.lang.Exception
	 */
	public void initialize(T entity, AccessibleWindow window) throws Exception {
		this.accesible = window;
		initialize(entity);
	}

	/**
	 * Metodo starta janela
	 *
	 * @param entity
	 * @throws java.lang.Exception
	 */
	public void initialize(T entity) throws Exception {
		if (entity == null) {
			throw new RuntimeException("Required entity not null");
		}
		this.model = entity;
		setModelFromFields();
		setLocationRelativeTo(null);
		start();
		this.setVisible(true);
	}

	protected void executeSave() {
		try {
			save();
			this.model = null;
			getModelFromFields();
		} catch (Exception e) {
			LabsMessageDialog.error(e);
		}
	}

	protected void executeAccessibleSave() throws Exception {
		try {
			this.model = getModelFromFields();
			accesible.comunicationWindow((AccessibleWindow) this);
			this.model = null;
			clearFields();
		} catch (Exception e) {
			LabsMessageDialog.error(e);
		}
	}

	@Override
	public void executeCancel() {
		if (hasFieldValue()) {
			if (LabsMessageDialog.confirmDialog("Deseja realmente cancelar ? todas as alterações serão perdidas",
					"Cancelar Edição")) {
				this.model = null;
				closeAndCleanFields();
			}
		} else {
			this.model = null;
			closeAndCleanFields();
		}
	}

	protected ShortCutKey cancelar = new ShortCutKey(2);

	/**
	 * Classe aninhada que define shortcut
	 */
	protected class ShortCutKey extends AbstractAction {

		private static final long serialVersionUID = -1961330223210587227L;
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

	public T getModel() {
		return model;
	}

}

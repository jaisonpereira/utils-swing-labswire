/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs;

import javax.swing.JOptionPane;

import com.wirelabs.exceptions.LabsValidationException;

/**
 *
 * @author jpereira
 */
public class LabsMessageDialog {

	private static final String SIM = "Sim";
	private static final String NAO = "Não";

	public static final int RESPOSTA_SIM = 0;

	/**
	 * Dialog realiza perguntas para o usuario
	 *
	 * @param pergunta
	 * @param tituloJanela
	 * @return
	 */
	public static boolean confirmDialog(String pergunta, String tituloJanela) {
		final Object[] options = { SIM, NAO };
		return RESPOSTA_SIM == JOptionPane.showOptionDialog(null, pergunta, tituloJanela, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, 1);
	}

	public static void error(Exception e) {
		if (e instanceof LabsValidationException) {
			errorValidation(e.getMessage());
		} else {
			e.printStackTrace();
			error(e.getMessage());
		}
	}

	/**
	 * Msg de erro padrao das janelas swing
	 *
	 * @param msg
	 */
	public static void error(String msg) {
		JOptionPane.showMessageDialog(null, "ERRO: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Msg de erro padrao das janelas swing
	 *
	 * @param msg
	 */
	public static void errorValidation(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Validação", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exit() {
		final Object[] options = { SIM, NAO, };
		final int choice = JOptionPane.showOptionDialog(null, "Deseja Realmente sair do sistema?", "Sair do Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 1);
		if (choice == 0) {
			System.exit(0);
		}
	}

	public static void info(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * Executa uma saida forcada no sistema
	 */
	public static void saidaForcada() {
		JOptionPane.showMessageDialog(null, "O Sistema será fechado", "O Sistema sera fechado ", 0);
		System.exit(0);
	}
}

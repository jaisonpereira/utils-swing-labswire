/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.dialogs.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jpereira
 */
public class LabsTableModel extends AbstractTableModel {

    JTable table;
    private ArrayList linhas = null;
    private Object[] colunas = null;

    public LabsTableModel(JTable table, ArrayList lin) {
        this.table = table;
        setLinhas(lin);
    }

    /**
     * Metodo cria colunas da tabela
     *
     * @param colunas
     * @return
     */
    public LabsTableModel makeColuns(ColunsLabs... colunas) {
        List colunasNome = new ArrayList();
        List colunasTamanho = new ArrayList();
        for (int i = 0; i < colunas.length; i++) {
            ColunsLabs col = colunas[i];
            colunasNome.add(col.getDescricao());
        }
        setColunas(colunasNome.toArray());
        table.setModel(this);
        for (int i = 0; i < colunas.length; i++) {
            ColunsLabs col = colunas[i];
            //oculta coluna id
            if (col.isHide()) {
                table.getColumnModel().getColumn(i).setMinWidth(0);
                table.getColumnModel().getColumn(i).setMaxWidth(0);
                table.getColumnModel().getColumn(i).setWidth(0);
                continue;
            }
            table.getColumnModel().getColumn(i).setPreferredWidth(col.getSize());//altura
            table.getColumnModel().getColumn(i).setResizable(true);
        }
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(true);
        table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return this;
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList dados) {
        linhas = dados;
    }

    public Object[] getColunas() {
        return colunas;
    }

    public void setColunas(Object[] nomes) {
        colunas = nomes;
    }

    public int getColumnCount() {
        return colunas.length;

    }

    public int getRowCount() {//retorna qtd de linhas
        return linhas.size();
    }

    public String getColumnName(int numCol) {//retorna nome da coluna
        return colunas[numCol].toString();

    }

    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);
        return linha[numCol];
    }

    /**
     * Seta itens padroes de menu alterar / excluir
     *
     * @return
     */
    public void exampleTableMenuItem() {
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem alterar = new JMenuItem("Alterar");
        JMenuItem deletar = new JMenuItem("Excluir");
        deletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "excluiu");
            }
        });
        alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "alterado");
            }
        });
        popupMenu.add(alterar);
        popupMenu.add(deletar);
        table.setComponentPopupMenu(popupMenu);
    }

    public void setMenuItensForRightClick(JMenuItem... itens) {
        final JPopupMenu popupMenu = new JPopupMenu();
        for (JMenuItem iten : itens) {
            popupMenu.add(iten);
        }
        table.setComponentPopupMenu(popupMenu);
    }

}

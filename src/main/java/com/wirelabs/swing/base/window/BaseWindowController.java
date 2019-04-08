/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

/**
 *
 * @author jpereira Interface defina comportamentos comuns entre classes
 *
 */
public interface BaseWindowController {

    abstract void clearFields();

    abstract boolean hasFieldValue();

    abstract void setTargetWindow(Object dialog);

    abstract void closeAndCleanFields();

    abstract void executeCancel();

}

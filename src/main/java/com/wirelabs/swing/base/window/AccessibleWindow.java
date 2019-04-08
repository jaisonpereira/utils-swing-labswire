/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.base.window;

/**
 *
 * @author jpereira Interface define hierarquia de janelas
 *
 */
public interface AccessibleWindow {

    abstract void comunicationWindow(AccessibleWindow accessible) throws Exception;

}

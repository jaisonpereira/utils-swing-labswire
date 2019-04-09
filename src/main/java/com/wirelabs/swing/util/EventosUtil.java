/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wirelabs.swing.util;

import java.awt.event.KeyEvent;

/**
 *
 * @author jpereira
 */
public class EventosUtil {

	public static boolean getEnterEvent(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			return true;
		}
		return false;
	}

}

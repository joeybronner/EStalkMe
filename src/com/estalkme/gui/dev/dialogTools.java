package com.estalkme.gui.dev;

import javax.swing.JOptionPane;

public class dialogTools {
	
	public static void showErrorMsg(String title, String msg) throws Exception
	{
		// Error Prompt
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}

}

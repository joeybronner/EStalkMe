package com.estalkme.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GUILoading {
	
	public static JFrame loadingFrame() {
		final JFrame frame = new JFrame("");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		final JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new JLabel("Chargement..."), BorderLayout.NORTH);
		contentPane.add(progressBar, BorderLayout.CENTER);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}
	
}

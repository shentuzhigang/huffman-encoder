package main;

import java.awt.EventQueue;

import netchat.HuffmanServer;
import swing.HuffmanJFrame;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanJFrame frame = new HuffmanJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if(false) {
			new Thread(new Runnable() {
				public void run() {
					try {
						HuffmanServer huffmanServer = new HuffmanServer();
						huffmanServer.start(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}

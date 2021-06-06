/**
 * 
 */
package swing;

import java.awt.*;

import javax.swing.*;

import huffman.*;
/**
 * @author ShenTuZhiGang
 *
 */
public class TreeJComponent extends JComponent{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;

	private static final int DEFAULT_WIDTH = 1600;
	private static final int DEFAULT_HEIGHT = 800;
	
	HuffmanTree HTree;
	public TreeJComponent(HuffmanTree HTree) {
		this.HTree=HTree;
	}
	public void paintComponent(Graphics g){
		paintHuffmanTree(g);
	}
	
	public void paintHuffmanTree(Graphics g){
		HTree.paintTree(HTree.getNode_tot(), g,800,20);
	}
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}

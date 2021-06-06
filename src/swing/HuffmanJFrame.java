package swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import huffman.*;
import netchat.*;

public class HuffmanJFrame extends JFrame {
	/**
	 * Main JPanel
	 */
	private JPanel contentPane;
	/**
	 * HuffmanTree
	 */
	private HuffmanTree HTree;
	/**
	 * About JDialog
	 */
	private AboutJDialog a;
	/**
	 * Frame real-time location information
	 */
	private int MAIN_FRAME_LOC_X;
	private int MAIN_FRAME_LOC_Y;
	/**
	 * Frame real-time size information
	 */
	private int MAIN_FRAME_WIDTH;
	private int MAIN_FRAME_HEIGHT;
	/**
	 * 
	 */
	private static final long serialVersionUID = 246433223587517805L;
	/**
	 * Frame width
	 */
	private static final int DEFAULT_WIDTH = 1600;
	/**
	 * Frame height
	 */
	private static final int DEFAULT_HEIGHT = 900;
	/**
	 * Network Socket
	 */
	private NetSocket nsk;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
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
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public HuffmanJFrame() {
		init();
		//改变观感
		 try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		// JFrame 初始化
		setTitle("Huffman编译码器");
        setIconImage(Toolkit.getDefaultToolkit().getImage(HuffmanJFrame.class.getResource("/swing/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		int screenWidth = (int) this.getToolkit().getScreenSize().getWidth();
		int screenHeight = (int) this.getToolkit().getScreenSize().getHeight();
		setLocation((screenWidth - getWidth())/2,(screenHeight - getHeight())/2);
		
		// JFrame Listener
		addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					Component comp = e.getComponent();
					MAIN_FRAME_HEIGHT=comp.getHeight();
					MAIN_FRAME_WIDTH=comp.getWidth();
					//System.out.println("当前窗口在大小：" + comp.getHeight() + "," + comp.getWidth());
				}
				@Override
				public void componentMoved(ComponentEvent e) {
					Component comp = e.getComponent();
					//System.out.println("当前窗口在屏幕中的位置：" + comp.getX() + "," + comp.getY());
					//更新当前窗口所在的坐标
					MAIN_FRAME_LOC_X = comp.getX();
					MAIN_FRAME_LOC_Y = comp.getY();
				}
			});  
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u83DC\u5355");
		menu.setFont(new Font("楷体", Font.PLAIN, 15));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6253\u5F00");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuItem.setFont(new Font("楷体", Font.PLAIN, 15));
		menu.add(menuItem);
		
		JMenuItem menuItem_0 = new JMenuItem("\u6811\u56FE");
		menuItem_0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		menuItem_0.setFont(new Font("楷体", Font.PLAIN, 15));
		menuItem_0.addActionListener(e->{
				if(HTree==null) {
					JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					
					TreeJDialog dialog = new TreeJDialog(HTree,this,"HuffmanTree图",true);
					int x = MAIN_FRAME_LOC_X - (int) dialog.getWidth() / 2 + MAIN_FRAME_WIDTH/2;
					int y = MAIN_FRAME_LOC_Y - (int) dialog.getHeight() / 2 + MAIN_FRAME_HEIGHT/2;
					dialog.setLocation(x, y);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		menu.add(menuItem_0);
		
		JMenuItem menuItem_quit = new JMenuItem("\u9000\u51FA");
		menuItem_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuItem_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem_quit.setFont(new Font("楷体", Font.PLAIN, 15));
		menu.add(menuItem_quit);
		
		JMenu menu_1 = new JMenu("\u5173\u4E8E");
		menu_1.setFont(new Font("楷体", Font.PLAIN, 15));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5F00\u53D1\u8005\u4FE1\u606F(A)",'A');
		menuItem_2.addActionListener(e->{
			if(a==null)
				a=new AboutJDialog(this,"开发者信息",true);
			int x = MAIN_FRAME_LOC_X - (int) a.getWidth() / 2 + MAIN_FRAME_WIDTH/2;
			int y = MAIN_FRAME_LOC_Y - (int) a.getHeight() / 2 + MAIN_FRAME_HEIGHT/2;
			a.setLocation(x, y);
			//a.setLocation(this.getWidth()-a.getWidth()/2, this.getHeight()/2-a.getHeight()/2);
			a.setVisible(true);
		});
		menuItem_2.setBounds(0, 0, 18, 18);
		ImageIcon ii = new ImageIcon(HuffmanJFrame.class.getResource("/swing/kfzicon.png"));
		//根据按钮大小改变图片大小
		menuItem_2.setIcon(new ImageIcon(ii.getImage().getScaledInstance(menuItem_2.getWidth(), menuItem_2.getHeight(), ii.getImage().SCALE_DEFAULT)));
		menuItem_2.setFont(new Font("楷体", Font.PLAIN, 15));
		menu_1.add(menuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JPanel HuffmanPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, HuffmanPanel, 17, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, HuffmanPanel, 31, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, HuffmanPanel, -500, SpringLayout.EAST, panel);
		HuffmanPanel.setBorder(new TitledBorder(null, "HuffmanTree", TitledBorder.LEADING, TitledBorder.TOP, new Font("楷体", Font.PLAIN, 15), null));
		panel.add(HuffmanPanel);
		SpringLayout sl_HuffmanPanel = new SpringLayout();
		HuffmanPanel.setLayout(sl_HuffmanPanel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u9ED8\u8BA4\u5B57\u7B26\u96C6\u548C\u9891\u7387");
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 0, SpringLayout.NORTH, HuffmanPanel);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 10, SpringLayout.WEST, HuffmanPanel);
		chckbxNewCheckBox.setToolTipText("\u4F7F\u7528\u9ED8\u8BA4\u5B57\u7B26\u96C6\u548C\u9891\u7387\u5EFA\u7ACBHuffmanTree");
		sl_panel.putConstraint(SpringLayout.SOUTH, chckbxNewCheckBox, -24, SpringLayout.SOUTH, HuffmanPanel);
		sl_panel.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, -71, SpringLayout.EAST, HuffmanPanel);
		HuffmanPanel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setFont(new Font("楷体", Font.PLAIN, 15));
		
		JLabel label_7 = new JLabel("");
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, label_7, -3, SpringLayout.NORTH, chckbxNewCheckBox);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, label_7, 16, SpringLayout.EAST, chckbxNewCheckBox);
		label_7.setIcon(new ImageIcon(HuffmanJFrame.class.getResource("/swing/W.png")));
		HuffmanPanel.add(label_7);
		
		JLabel lblhuffmantree = new JLabel("\u901A\u8FC7\u5B57\u7B26\u96C6\u548C\u9891\u7387\u6587\u4EF6\u5EFA\u7ACBHuffmanTree");
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, lblhuffmantree, 17, SpringLayout.SOUTH, chckbxNewCheckBox);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, lblhuffmantree, 0, SpringLayout.WEST, chckbxNewCheckBox);
		lblhuffmantree.setEnabled(false);
		lblhuffmantree.setFont(new Font("楷体", Font.PLAIN, 15));
		HuffmanPanel.add(lblhuffmantree);
		
		JButton btnNewButton_0 = new JButton("\u6253\u5F00\u6587\u4EF6");
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, btnNewButton_0, -4, SpringLayout.NORTH, lblhuffmantree);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, btnNewButton_0, 35, SpringLayout.EAST, lblhuffmantree);
		btnNewButton_0.setToolTipText("\u6253\u5F00\u6587\u4EF6");
		btnNewButton_0.setEnabled(false);
		btnNewButton_0.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton_0, 47, SpringLayout.WEST, HuffmanPanel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton_0, -22, SpringLayout.SOUTH, HuffmanPanel);
		HuffmanPanel.add(btnNewButton_0);
		
		JPanel FilePanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, HuffmanPanel, -21, SpringLayout.NORTH, FilePanel);
		sl_panel.putConstraint(SpringLayout.NORTH, FilePanel, 179, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, FilePanel, 2, SpringLayout.WEST, HuffmanPanel);
		sl_panel.putConstraint(SpringLayout.EAST, FilePanel, 0, SpringLayout.EAST, HuffmanPanel);
		FilePanel.setBorder(new TitledBorder(null, "\u6587\u4EF6\u7F16\u7801\u8BD1\u7801", TitledBorder.LEADING, TitledBorder.TOP, new Font("楷体", Font.PLAIN, 15), null));
		panel.add(FilePanel);
		SpringLayout sl_FilePanel = new SpringLayout();
		FilePanel.setLayout(sl_FilePanel);
		
		JButton btnNewButton_1 = new JButton("\u6587\u4EF6\u7F16\u7801");
		btnNewButton_1.setIcon(new ImageIcon(HuffmanJFrame.class.getResource("/swing/txt.png")));
		sl_FilePanel.putConstraint(SpringLayout.NORTH, btnNewButton_1, 10, SpringLayout.NORTH, FilePanel);
		btnNewButton_1.setToolTipText("\u6253\u5F00\u6587\u4EF6\u4EE5\u5BF9\u6587\u4EF6\u5185\u5BB9\u8FDB\u884CHuffman\u7F16\u7801");
		btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton_1, 100, SpringLayout.NORTH, FilePanel);
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton_1, 20, SpringLayout.EAST, FilePanel);
		FilePanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u6587\u4EF6\u8BD1\u7801");
		btnNewButton_2.setIcon(new ImageIcon(HuffmanJFrame.class.getResource("/swing/bin.png")));
		sl_FilePanel.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, btnNewButton_2);
		sl_FilePanel.putConstraint(SpringLayout.WEST, btnNewButton_2, 10, SpringLayout.WEST, FilePanel);
		btnNewButton_2.setToolTipText("\u6253\u5F00\u6587\u4EF6\u4EE5\u5BF9\u6587\u4EF6\u5185\u5BB9\u8FDB\u884CHuffman\u8BD1\u7801");
		sl_FilePanel.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -10, SpringLayout.SOUTH, FilePanel);
		btnNewButton_2.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton_2, 43, SpringLayout.NORTH, FilePanel);
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton_2, 55, SpringLayout.WEST, FilePanel);
		FilePanel.add(btnNewButton_2);
		
		JLabel label_1 = new JLabel("\u6587\u4EF6\u5730\u5740\uFF1A");
		sl_FilePanel.putConstraint(SpringLayout.NORTH, label_1, 4, SpringLayout.NORTH, btnNewButton_1);
		label_1.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_FilePanel.putConstraint(SpringLayout.NORTH, label_1, 4, SpringLayout.NORTH, btnNewButton_1);
		sl_FilePanel.putConstraint(SpringLayout.WEST, label_1, 19, SpringLayout.EAST, btnNewButton_1);
		FilePanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u6587\u4EF6\u5730\u5740\uFF1A");
		label_2.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_FilePanel.putConstraint(SpringLayout.NORTH, label_2, 4, SpringLayout.NORTH, btnNewButton_2);
		sl_FilePanel.putConstraint(SpringLayout.WEST, label_2, 19, SpringLayout.EAST, btnNewButton_2);
		FilePanel.add(label_2);
		
		JPanel TextPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, FilePanel, -15, SpringLayout.NORTH, TextPanel);
		sl_panel.putConstraint(SpringLayout.NORTH, TextPanel, 324, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, TextPanel, 31, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, TextPanel, 0, SpringLayout.EAST, HuffmanPanel);
		TextPanel.setBorder(new TitledBorder(null, "\u5373\u65F6\u7F16\u7801\u8BD1\u7801", TitledBorder.LEADING, TitledBorder.TOP, new Font("楷体", Font.PLAIN, 15), null));
		
		JLabel lblhuffmantree_1 = new JLabel("\u901A\u8FC7\u6587\u672C\u6587\u4EF6\u5206\u6790\u5EFA\u7ACBHuffmanTree");
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, lblhuffmantree_1, 10, SpringLayout.WEST, HuffmanPanel);
		sl_HuffmanPanel.putConstraint(SpringLayout.SOUTH, lblhuffmantree_1, -15, SpringLayout.SOUTH, HuffmanPanel);
		lblhuffmantree_1.setEnabled(false);
		lblhuffmantree_1.setFont(new Font("楷体", Font.PLAIN, 15));
		HuffmanPanel.add(lblhuffmantree_1);
		
		JButton btnNewButton = new JButton("\u6253\u5F00\u6587\u4EF6");
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, btnNewButton, -4, SpringLayout.NORTH, lblhuffmantree_1);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, btnNewButton_0);
		btnNewButton.setToolTipText("\u6253\u5F00\u6587\u4EF6");
		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("楷体", Font.PLAIN, 15));
		HuffmanPanel.add(btnNewButton);
		
		JLabel lblHuffman_1 = new JLabel("Huffman\u5B57\u7B26\u5F62\u5F0F\uFF1A");
		sl_panel.putConstraint(SpringLayout.NORTH, lblHuffman_1, -3, SpringLayout.NORTH, HuffmanPanel);
		sl_panel.putConstraint(SpringLayout.WEST, lblHuffman_1, 0, SpringLayout.EAST, HuffmanPanel);
		lblHuffman_1.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(lblHuffman_1);
		
		JTextArea textArea_3 = new JTextArea();
		sl_panel.putConstraint(SpringLayout.NORTH, textArea_3, 6, SpringLayout.SOUTH, lblHuffman_1);
		sl_panel.putConstraint(SpringLayout.WEST, textArea_3, 0, SpringLayout.WEST, lblHuffman_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea_3, 0, SpringLayout.SOUTH, HuffmanPanel);
		textArea_3.setWrapStyleWord(true);
		textArea_3.setLineWrap(true);
		textArea_3.setEnabled(false);
		textArea_3.setEditable(false);
		textArea_3.setFont(new Font("楷体", Font.PLAIN, 13));
		panel.add(textArea_3);
		
		
		
		JLabel lblHuffman = new JLabel("Huffman\u7F16\u7801\u8868\uFF1A");
		sl_panel.putConstraint(SpringLayout.NORTH, lblHuffman, 6, SpringLayout.SOUTH, textArea_3);
		sl_panel.putConstraint(SpringLayout.WEST, lblHuffman, 0, SpringLayout.WEST, lblHuffman_1);
		lblHuffman.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(lblHuffman);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEnabled(false);
		//panel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		sl_panel.putConstraint(SpringLayout.EAST, textArea_3, 0, SpringLayout.EAST, scrollPane);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblHuffman_1);
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 190, SpringLayout.NORTH, panel);
		//sl_panel.putConstraint(SpringLayout.WEST, scrollPane, -307, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, -25, SpringLayout.EAST, panel);
		panel.add(scrollPane);
		//Create default HuffmanTree
		initHuffmanTree(textArea,textArea_3);
		
		panel.add(TextPanel);
		SpringLayout sl_TextPanel = new SpringLayout();
		TextPanel.setLayout(sl_TextPanel);
		
		
		JButton button = new JButton("\u7F16\u7801");
		
		sl_TextPanel.putConstraint(SpringLayout.NORTH, button, 20, SpringLayout.NORTH, TextPanel);
		sl_TextPanel.putConstraint(SpringLayout.WEST, button, 10, SpringLayout.WEST, TextPanel);
		button.setToolTipText("\u5BF9\u6587\u672C\u57DF\u5185\u5BB9\u7F16\u7801");
		button.setFont(new Font("楷体", Font.PLAIN, 15));
		TextPanel.add(button);
		
		JButton button_1 = new JButton("\u8BD1\u7801");
		sl_TextPanel.putConstraint(SpringLayout.WEST, button_1, 0, SpringLayout.WEST, button);
		sl_TextPanel.putConstraint(SpringLayout.SOUTH, button_1, 41, SpringLayout.SOUTH, button);
		button_1.setToolTipText("\u5BF9\u6587\u672C\u57DF\u5185\u5BB9\u8BD1\u7801");
		button_1.setFont(new Font("楷体", Font.PLAIN, 15));
		TextPanel.add(button_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setRows(4);
		//TextPanel.add(textArea_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		sl_TextPanel.putConstraint(SpringLayout.NORTH, scrollPane_1, 12, SpringLayout.NORTH, TextPanel);
		sl_TextPanel.putConstraint(SpringLayout.WEST, scrollPane_1, 18, SpringLayout.EAST, button);
		sl_TextPanel.putConstraint(SpringLayout.SOUTH, scrollPane_1, -8, SpringLayout.SOUTH, TextPanel);
		sl_TextPanel.putConstraint(SpringLayout.EAST, scrollPane_1, -21, SpringLayout.EAST, TextPanel);
		TextPanel.add(scrollPane_1);
		
		
		btnNewButton_0.addActionListener(e->{
				FileDialog jf=new FileDialog(this,"选择字符集和频度的文件",FileDialog.LOAD);
				jf.setVisible(true);
				String filepath=getAllPath(jf);
				if(filepath==null) {
					return;
				}
				File file = new File(filepath);
				FileReader fr;
				int lines = 0;
				try {
					fr = new FileReader(file);
					@SuppressWarnings("resource")
					LineNumberReader reader = new LineNumberReader(fr);
			        reader.skip(Long.MAX_VALUE);
			        lines = reader.getLineNumber();
				} catch (IOException ioe) {
					// TODO 自动生成的 catch 块
					ioe.printStackTrace();
				}
				System.out.println(lines);
				char[] code = new char[lines/2];
				double[] weight =new double[lines/2];
				FileInputStream in=null;
				try {
					
					in=new FileInputStream(file);
					@SuppressWarnings("resource")
					Scanner input=new Scanner(in);
					int i=0;
					while(input.hasNext()) {
						code[i]=input.nextLine().charAt(0);
						System.out.print(code[i]+":");
						weight[i]=input.nextDouble();
						System.out.println(weight[i]);
						input.nextLine();
						i++;
					}
					
				} catch(IOException ioe) {
					JOptionPane.showMessageDialog(null, "文件读取错误", "错误提示",JOptionPane.ERROR_MESSAGE);
					ioe.printStackTrace();
				} finally {  
		        	try {
						in.close();
					} catch (IOException ioe) {
						// TODO 自动生成的 catch 块
						ioe.printStackTrace();
					}  
		        }  
				initHuffmanTree(code,weight,textArea,textArea_3);
			});
		btnNewButton.addActionListener(e->{
				FileDialog jf=new FileDialog(this,"选择文本文件",FileDialog.LOAD);
				jf.setVisible(true);
				String path=getAllPath(jf);
				if(path==null) {
					return;
				}
				String str=readFileToString(path);
				int len=str.length();
				char[] code = new char[128];
				double[] weight =new double[128];
				for(int i=0;i<128;i++) {
					code[i]=(char)i;
				}
				int num=0;
				for(int i=0;i<len;i++) {
					char ch=str.charAt(i);
					if(ch<0||ch>128) {
						JOptionPane.showMessageDialog(null, "文件读取错误", "错误提示",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(weight[ch]==0)
						num++;
					weight[ch]+=1;
				}
				char[] new_code = new char[num];
				double[] new_weight =new double[num];
				for(int i=0,j=0;i<128;i++) {
					if(weight[i]!=0.0) {
						new_code[j]=code[i];
						new_weight[j++]=weight[i];
					}
				}
				initHuffmanTree(new_code,new_weight,textArea,textArea_3);
			});
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) {
					initHuffmanTree(textArea,textArea_3);
					btnNewButton.setEnabled(false);
					btnNewButton_0.setEnabled(false);
					lblhuffmantree.setEnabled(false);
					lblhuffmantree_1.setEnabled(false);
				}else {
					HTree=null;
					btnNewButton.setEnabled(true);
					btnNewButton_0.setEnabled(true);
					lblhuffmantree.setEnabled(true);
					lblhuffmantree_1.setEnabled(true);
					textArea.setText("");
					textArea_3.setText("");
				}
			}
		});
		btnNewButton_1.addActionListener(e->{
				if(HTree==null) {
					JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				FileDialog jf=new FileDialog(this,"选择文本文件",FileDialog.LOAD);
				jf.setVisible(true);
				String path=getAllPath(jf);
				if(path==null) {
					label_1.setText("读取文件错误:文件不存在或者未选择文件");
					return;
				}
				String str=readFileToString(path);
				System.out.println(path+":"+str);
				label_1.setText("文件地址:"+path);
				String enstr=HTree.enCode(str);
				if(enstr==null) {
					label_1.setText("文件错误:文件内容存在不可编码字符");
					return;
				}
				System.out.println(enstr);
				FileDialog sjf=new FileDialog(this,"选择保存二进制文本文件的位置",FileDialog.SAVE);
				sjf.setFile("CodeFile");
				sjf.setVisible(true);
				String spath=getAllPath(sjf);
				if(spath==null) {
					label_1.setText("保存文件错误:文件不存在或者未选择文件");
					return;
				}
				saveStringToFile(spath,enstr);
				label_2.setText("文件地址:"+spath);
			});
		btnNewButton_2.addActionListener(e->{
				if(HTree==null) {
					JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FileDialog jf=new FileDialog(this,"选择二进制文本文件",FileDialog.LOAD);
				jf.setVisible(true);
				String path=getAllPath(jf);
				if(path==null) {
					label_2.setText("读取文件错误:文件不存在或者未选择文件");
					return;
				}
				String str=readFileToString(path);
				System.out.println(path+":"+str);
				label_2.setText("文件地址:"+path);
				String destr=HTree.deCode(str);
				if(destr==null) {
					label_2.setText("文件错误:文件内容存在不可译码的二进制内容");
					return;
				}
				System.out.println(destr);
				FileDialog sjf=new FileDialog(this,"选择保存文本文件的位置",FileDialog.SAVE);
				sjf.setFile("TextFile");
				sjf.setVisible(true);
				String spath=getAllPath(sjf);
				if(spath==null) {
					label_2.setText("保存文件错误:文件不存在或者未选择文件");
					return;
				}
				saveStringToFile(spath,destr);
				label_1.setText("文件地址:"+path);
			});
		button.addActionListener(e->{
			if(HTree==null) {
				JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String enstr=HTree.enCode(textArea_1.getText());
			if(enstr==null) {
				textArea_1.setText("错误:内容存在不可编码字符");
				return;
			}
			textArea_1.setText(enstr);
		});
		button_1.addActionListener(e->{
			if(HTree==null) {
				JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String destr=HTree.deCode(textArea_1.getText());
			if(destr==null) {
				textArea_1.setText("错误:内容存在不可译码字符");
				return;
			}
			textArea_1.setText(destr);
		});
		
		JPanel NetPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, NetPanel, 493, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, NetPanel, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, TextPanel, -14, SpringLayout.NORTH, NetPanel);
		sl_panel.putConstraint(SpringLayout.WEST, NetPanel, 32, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, NetPanel, 0, SpringLayout.EAST, HuffmanPanel);
		
		JPanel OutPanel = new JPanel();
		OutPanel.setBorder(new TitledBorder(null, "\u5BFC\u51FA", TitledBorder.LEADING, TitledBorder.TOP, new Font("楷体", Font.PLAIN, 15), null));
		sl_HuffmanPanel.putConstraint(SpringLayout.NORTH, OutPanel, -5, SpringLayout.NORTH, HuffmanPanel);
		sl_HuffmanPanel.putConstraint(SpringLayout.WEST, OutPanel, 29, SpringLayout.EAST, btnNewButton_0);
		sl_HuffmanPanel.putConstraint(SpringLayout.SOUTH, OutPanel, -5, SpringLayout.SOUTH, HuffmanPanel);
		sl_HuffmanPanel.putConstraint(SpringLayout.EAST, OutPanel, -22, SpringLayout.EAST, HuffmanPanel);
		HuffmanPanel.add(OutPanel);
		SpringLayout sl_OutPanel = new SpringLayout();
		OutPanel.setLayout(sl_OutPanel);
		
		
		JButton btntree = new JButton("\u5BFC\u51FATreePrint");
		btntree.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_OutPanel.putConstraint(SpringLayout.NORTH, btntree, 5, SpringLayout.NORTH, OutPanel);
		sl_OutPanel.putConstraint(SpringLayout.WEST, btntree, 10, SpringLayout.WEST, OutPanel);
		OutPanel.add(btntree);
		
		JButton btncode = new JButton("\u5BFC\u51FACodePrint");
		btncode.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_OutPanel.putConstraint(SpringLayout.WEST, btncode, 10, SpringLayout.WEST, OutPanel);
		sl_OutPanel.putConstraint(SpringLayout.NORTH, btncode, 8, SpringLayout.SOUTH, btntree);
		OutPanel.add(btncode);
		
		JLabel label_5 = new JLabel("\u6587\u4EF6\u5730\u5740\uFF1A");
		sl_OutPanel.putConstraint(SpringLayout.NORTH, label_5, 4, SpringLayout.NORTH, btntree);
		sl_OutPanel.putConstraint(SpringLayout.WEST, label_5, 10, SpringLayout.EAST, btntree);
		label_5.setFont(new Font("楷体", Font.PLAIN, 15));
		OutPanel.add(label_5);
		
		
		JLabel label_6 = new JLabel("\u6587\u4EF6\u5730\u5740\uFF1A");
		sl_OutPanel.putConstraint(SpringLayout.NORTH, label_6, 4, SpringLayout.NORTH, btncode);
		sl_OutPanel.putConstraint(SpringLayout.WEST, label_6, 10, SpringLayout.EAST, btncode);
		label_6.setFont(new Font("楷体", Font.PLAIN, 15));
		OutPanel.add(label_6);
		
		
		
		btntree.addActionListener(e->{
			if(HTree==null) {
				JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FileDialog sjf=new FileDialog(this,"选择保存编码文本文件的位置",FileDialog.SAVE);
			sjf.setFile("TreePrint");
			sjf.setVisible(true);
			String spath=getAllPath(sjf);
			if(spath==null) {
				label_5.setText("保存文件错误:文件不存在或者未选择文件");
				return;
			}
			saveStringToFile(spath,HTree.HuffmanListString());
			label_5.setText("文件地址:"+spath);
		
		});
		
		btncode.addActionListener(e->{
			if(HTree==null) {
				JOptionPane.showMessageDialog(null, "未建立HuffmanTree和HuffmanCode。", "错误提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FileDialog sjf=new FileDialog(this,"选择保存编码文本文件的位置",FileDialog.SAVE);
			sjf.setFile("CodePrint");
			sjf.setVisible(true);
			String spath=getAllPath(sjf);
			if(spath==null) {
				label_6.setText("保存文件错误:文件不存在或者未选择文件");
				return;
			}
			saveStringToFile(spath,HTree.HuffmanCodeListString());
			label_6.setText("文件地址:"+spath);
		
		});
		NetPanel.setBorder(new TitledBorder(null, "\u7F51\u7EDC\u901A\u4FE1", TitledBorder.LEADING, TitledBorder.TOP, new Font("楷体", Font.PLAIN, 15), null));
		panel.add(NetPanel);
		SpringLayout sl_NetPanel = new SpringLayout();
		NetPanel.setLayout(sl_NetPanel);
		
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setWrapStyleWord(true);
		textArea_2.setRows(0);
		textArea_2.setEditable(false);
		textArea_2.setEnabled(false);
		textArea_2.setLineWrap(true);
		textArea_2.setFont(new Font("楷体", Font.PLAIN, 13));
		//NetPanel.add(textArea_2);
		
		JScrollPane textscrollPane = new JScrollPane(textArea_2);
		sl_NetPanel.putConstraint(SpringLayout.WEST, textscrollPane, 10, SpringLayout.WEST, NetPanel);
		sl_NetPanel.putConstraint(SpringLayout.SOUTH, textscrollPane, -70, SpringLayout.SOUTH, NetPanel);
		NetPanel.add(textscrollPane);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setEditable(false);
		sl_NetPanel.putConstraint(SpringLayout.NORTH, textArea_4, 7, SpringLayout.SOUTH, textArea_2);
		sl_NetPanel.putConstraint(SpringLayout.WEST, textArea_4, 10, SpringLayout.WEST, NetPanel);
		sl_NetPanel.putConstraint(SpringLayout.EAST, textArea_4, -16, SpringLayout.EAST, NetPanel);
		textArea_4.setWrapStyleWord(true);
		textArea_4.setFont(new Font("楷体", Font.PLAIN, 13));
		textArea_4.setRows(10);
		NetPanel.add(textArea_4);
		
		JButton button_2 = new JButton("\u53D1\u9001");
		button_2.addActionListener(e->{
				try {
					nsk.write(textArea_4.getText().getBytes());
					//textArea_2.setText(textArea_2.getText()+"Local Host:"+textArea_4.getText()+"\r\n");
					textArea_2.append("Local Host:"+textArea_4.getText()+"\r\n");
					textscrollPane.getVerticalScrollBar().setValue(textscrollPane.getVerticalScrollBar().getMaximum());
					//textArea_4.setText(""+(textscrollPane.getVerticalScrollBar().getValue()+" "+textscrollPane.getVerticalScrollBar().getMaximum()));
					textArea_4.setText("");
					textArea_4.requestFocus();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			});
		button_2.setEnabled(false);
		sl_NetPanel.putConstraint(SpringLayout.SOUTH, textArea_4, 0, SpringLayout.NORTH, button_2);
		sl_NetPanel.putConstraint(SpringLayout.EAST, textscrollPane, 0, SpringLayout.EAST, button_2);
		sl_NetPanel.putConstraint(SpringLayout.SOUTH, textArea_2, -47, SpringLayout.NORTH, button_2);
		button_2.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_NetPanel.putConstraint(SpringLayout.SOUTH, button_2, 0, SpringLayout.SOUTH, NetPanel);
		sl_NetPanel.putConstraint(SpringLayout.EAST, button_2, -16, SpringLayout.EAST, NetPanel);
		NetPanel.add(button_2);
		
		
		textArea_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						nsk.write(textArea_4.getText().getBytes());
						//textArea_2.setText(textArea_2.getText()+"Local Host:"+textArea_4.getText()+"\r\n");
						textArea_2.append("Local Host:"+textArea_4.getText()+"\r\n");
						textscrollPane.getVerticalScrollBar().setValue(textscrollPane.getVerticalScrollBar().getMaximum());
						textArea_4.setText("");
						textArea_4.requestFocus();
						e.consume();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		JButton btnNewButton_3 = new JButton("\u6E05\u7A7A");
		btnNewButton_3.addActionListener(e-> {
				textArea_2.setText("");
			});
		btnNewButton_3.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_NetPanel.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, button_2);
		sl_NetPanel.putConstraint(SpringLayout.EAST, btnNewButton_3, -18, SpringLayout.WEST, button_2);
		NetPanel.add(btnNewButton_3);
		
		JLabel lblIp = new JLabel("IP\uFF1A");
		lblIp.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(lblIp);
		
		textField = new JTextField();
		textField.setText("127.0.0.1");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, lblIp, 3, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.EAST, lblIp, -6, SpringLayout.WEST, textField);
		sl_NetPanel.putConstraint(SpringLayout.NORTH, textscrollPane, 6, SpringLayout.SOUTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.NORTH, textField, 7, SpringLayout.NORTH, NetPanel);
		sl_NetPanel.putConstraint(SpringLayout.WEST, textField, 51, SpringLayout.WEST, NetPanel);
		textField.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(textField);
		textField.setColumns(20);
		
		JLabel label = new JLabel("\u7AEF\u53E3\uFF1A");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, label, 3, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.WEST, label, 17, SpringLayout.EAST, textField);
		label.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setText("8889");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.EAST, label);
		textField_1.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(textField_1);
		textField_1.setColumns(8);
		
		JButton button_3 = new JButton("\u8FDE\u63A5");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, button_3, -1, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.WEST, button_3, 36, SpringLayout.EAST, textField_1);
		button_3.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(button_3);
		
		JButton button_4 = new JButton("\u65AD\u5F00");
		button_4.setEnabled(false);
		sl_NetPanel.putConstraint(SpringLayout.NORTH, button_4, -1, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.WEST, button_4, 10, SpringLayout.EAST, button_3);
		button_4.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(button_4);
		
		JLabel label_3 = new JLabel("\u8FDE\u63A5\u72B6\u6001\uFF1A");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, label_3, 3, SpringLayout.NORTH, textField);
		label_3.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u672A\u8FDE\u63A5");
		sl_NetPanel.putConstraint(SpringLayout.NORTH, label_4, 3, SpringLayout.NORTH, textField);
		sl_NetPanel.putConstraint(SpringLayout.EAST, label_3, -1, SpringLayout.WEST, label_4);
		sl_NetPanel.putConstraint(SpringLayout.WEST, label_4, 0, SpringLayout.WEST, btnNewButton_3);
		label_4.setFont(new Font("楷体", Font.PLAIN, 15));
		NetPanel.add(label_4);
		
		
		button_3.addActionListener(e->{
				String ip=textField.getText();
				int port=Integer.parseInt(textField_1.getText());
				try {
					nsk.open(ip, port);
				} catch (UnknownHostException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					label_4.setText("连接错误");
					return;
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					label_4.setText("I/O错误");
					return;
				} 
				label_4.setText("已连接");
				new Thread(()->{
					byte[] buf=new byte[1024];
					int len=0;
					try {
						while((len = nsk.read(buf))!=-1) {
							textArea_2.append("Remote Host:"+new String(buf,0,len)+"\r\n");
							textscrollPane.getVerticalScrollBar().setValue(textscrollPane.getVerticalScrollBar().getMaximum());
						}
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
						label_4.setText("失去连接");
						
					}finally {
						try {
			                nsk.shutdownInput();
			                nsk.close();
			            } catch (IOException e1) {
			                e1.printStackTrace();
			            }
					}
				}).start(); 
				textArea_4.setEditable(true);
				textArea_4.requestFocus();
				button_2.setEnabled(true);
				button_3.setEnabled(false);
				button_4.setEnabled(true);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
			});
		button_4.addActionListener(e->{
				try {
					nsk.shutdownOutput();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					label_4.setText("I/O错误");
					return;
				}finally {
					label_4.setText("已断开");
					textArea_4.setEditable(false);
					button_2.setEnabled(false);
					button_3.setEnabled(true);
					button_4.setEnabled(false);
					textField.setEnabled(true);
					textField_1.setEnabled(true);
				}
			});
		
		JCheckBox checkBox = new JCheckBox("\u6D88\u606F\u63A5\u6536");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox.isSelected()) {
					try {
						nsk.startReceive(555,textArea_2);
						textArea_2.append("message reception is on!\r\n");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else {
					nsk.stopReceive();
					textArea_2.append("message reception is off!\r\n");
				}
				
			}
		});
		checkBox.setSelected(true);
		checkBox.setFont(new Font("楷体", Font.PLAIN, 15));
		sl_NetPanel.putConstraint(SpringLayout.WEST, checkBox, 28, SpringLayout.EAST, button_4);
		sl_NetPanel.putConstraint(SpringLayout.SOUTH, checkBox, -6, SpringLayout.NORTH, textscrollPane);
		NetPanel.add(checkBox);
		
		
		try {
			nsk.startReceive(555,textArea_2);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	/**
	 * initialization
	 */
	private void init() {
		nsk=new NetSocket();
		a=null;
	}
		
	/**
	 * @param tc - JTextComponent对象
	 * @param tc1 - JTextComponent对象
	 */
	public void initHuffmanTree(JTextComponent tc,JTextComponent tc1) {
		HTree=new HuffmanTree();
		HTree.createHuffmanCode();
		tc1.setText("");
		tc.setText("");
		tc1.setText(HTree.toString());
		tc.setText(HTree.HuffmanCodeListString());
	}
	/**
	 * @param code - 字符集
	 * @param weight - 频数
	 * @param tc - JTextComponent对象
	 * @param tc1 - JTextComponent对象
	 */
	public void initHuffmanTree(char[] code,double[] weight,JTextComponent tc,JTextComponent tc1) {
		HTree=new HuffmanTree(code,weight);
		HTree.createHuffmanCode();
		tc1.setText("");
		tc.setText("");
		tc1.setText(HTree.toString());
		tc.setText(HTree.HuffmanCodeListString());
	}
	/**
	 * @param fileName - 文件路径
	 * @return encode - 文件内容的字符串
	 */
	public String readFileToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        FileInputStream in=null;
        try {  
            in = new FileInputStream(file);  
            in.read(filecontent);  
            return new String(filecontent, encoding);
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;
        } finally {  
        	try {
				in.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  
        }  
    }  
	/**
	 * @param fileName - 文件路径
	 * @param content - 需要写入文件的内容
	 */
	public void saveStringToFile(String fileName,String content){
		FileWriter writer=null;
		try {
			writer = new FileWriter(fileName);
			writer.write(content);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param fd- FileDialog对象
	 * @return FileAllPath - FileDialog选择的文件完整路径，如果没有返回null
	 */
	public String getAllPath(FileDialog fd) {  
		String path=fd.getDirectory();
		String filename=fd.getFile();
		if(path==null||filename==null)
			return null;
		return path+filename;
    }  
}



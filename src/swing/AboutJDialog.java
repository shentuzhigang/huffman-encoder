package swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;



public class AboutJDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4149837440466129389L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutJDialog dialog = new AboutJDialog(null,"测试",false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个具有指定标题，模态和指定所有者的 Dialog 。 
	 * @param owner 指定所有者
	 * @param title 指定标题
	 * @param modal 模态
	 */
	public AboutJDialog(JFrame owner,String title,boolean modal) {
		// AboutJDialog 初始化
		super(owner,title,modal);
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutJDialog.class.getResource("/swing/kfzicon.png")));
		setResizable(false);
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
		setModal(modal);
		setBounds(100, 100, 500, 300);
		//setLocation(owner.getWidth()/2-getWidth()/2, owner.getHeight()/2-getHeight()/2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblNewLabel = new JLabel("");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblNewLabel, 190, SpringLayout.WEST, contentPanel);
			lblNewLabel.setIcon(new ImageIcon(AboutJDialog.class.getResource("/swing/LOGO.png")));
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblNewLabel);
		}
		{
			JTextPane txtpnHuffman = new JTextPane();
			txtpnHuffman.setToolTipText("\u5F00\u53D1\u8005\u4FE1\u606F");
			txtpnHuffman.setEditable(false);
			txtpnHuffman.setFont(new Font("楷体", Font.PLAIN, 15));
			txtpnHuffman.setText("Huffman\u7F16\u7801\u8BD1\u7801\u5668\r\n\r\n\u5236\u4F5C\u4EBA\uFF1A\u7533\u5C60\u5FD7\u521A\r\nQQ\uFF1A1600337300\r\n\r\n\r\n\u65E5\u671F\uFF1A2019\u5E7412\u67088\u65E5");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, txtpnHuffman, 5, SpringLayout.NORTH, lblNewLabel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, txtpnHuffman, 27, SpringLayout.EAST, lblNewLabel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtpnHuffman, 0, SpringLayout.SOUTH, lblNewLabel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, txtpnHuffman, -21, SpringLayout.EAST, contentPanel);
			contentPanel.add(txtpnHuffman);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				 ClockJLabel cl= new ClockJLabel();
				 cl.setFont(new Font("楷体",Font.BOLD,16));
				 buttonPane.add(cl, BorderLayout.WEST);
			}
			{
				JButton okButton = new JButton("OK确定");
				okButton.addActionListener(e->{
					this.setVisible(false);
				});
				okButton.setFont(new Font("楷体", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, BorderLayout.EAST);
				getRootPane().setDefaultButton(okButton);
			}
			
		}
		
		
	}
}

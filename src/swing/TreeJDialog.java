package swing;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import huffman.HuffmanTree;
import javax.swing.JScrollPane;

public class TreeJDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539747011017098893L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TreeJDialog dialog = new TreeJDialog(new HuffmanTree(),null,"测试",true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TreeJDialog(HuffmanTree HTree,JFrame owner,String title,boolean modal) {
		// AboutJDialog 初始化
		super(owner,title,modal);
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutJDialog.class.getResource("/swing/kfzicon.png")));
		//setResizable(false);
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
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			
			
			TreeJComponent tj=new TreeJComponent(HTree);
			JScrollPane scrollPane = new JScrollPane(tj);
			//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			scrollPane.setBounds(0, 0, 500,500);
			contentPanel.add(scrollPane);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}
}

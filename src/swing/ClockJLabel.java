package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ClockJLabel extends JLabel {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
	}
	public ClockJLabel() {
		setFont(new Font("����",Font.BOLD,20));
		//���ñ�ǩ�����Լ���ǩ���ֵ���ɫ
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					//System.exit(0);
				}
			}
		});
		//ע�����������������¼���mouseAdapter�еķ������Ѿ���װ��ֱ��ʵ�ֹ��ܼ���
		
		//�����������̣߳�ʹʱ�䲻�ϵ���ʾ����Ļ��
		new Thread(new Runnable(){
			//�ڴ���Thread����ʱֱ��ʹ��һ��������Run�Ǳ��˶���ʵ���߳��еķ�����
			public void run() {
				while(true){
					//����ѭ������1000ms
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						//��׽�������߳��е��쳣����һ������ִ�е��̱߳��ж�ʱ�ͻ��������쳣�����catch�󵱳���˫��ֹͣʱ������ִ���
						e.printStackTrace();
					}
					setText(getDate());
					
				}
			}
			
		}).start();//����Thread���������Thread����Ҳ�������ġ�
	}
	private String getDate() {
		//�����������java�г����Ļ�ȡ��ǰʱ�䲢��ʽ�����������
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//ȷ��ʱ��ĸ�ʽ
		return df.format(new Date());
		//new Dateȡ��ǰϵͳʱ�䣬ʹ�ô����ĸ�ʽʵ����ʽ������ȥ����ʱ�䡣
	}
}

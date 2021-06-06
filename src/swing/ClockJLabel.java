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
		// TODO 自动生成的方法存根
		
	}
	public ClockJLabel() {
		setFont(new Font("黑体",Font.BOLD,20));
		//设置标签字体以及标签中字的颜色
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					//System.exit(0);
				}
			}
		});
		//注册监听器，监听鼠标事件，mouseAdapter中的方法都已经封装，直接实现功能即可
		
		//创建并启动线程，使时间不断地显示在屏幕上
		new Thread(new Runnable(){
			//在创建Thread对象时直接使用一个匿名的Run那边了对象实现线程中的方法。
			public void run() {
				while(true){
					//无限循环休眠1000ms
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						//扑捉发生在线程中的异常，当一个正在执行的线程被中断时就会出现这个异常，添加catch后当程序被双击停止时不会出现错误
						e.printStackTrace();
					}
					setText(getDate());
					
				}
			}
			
		}).start();//启动Thread方法，这个Thread方法也是匿名的。
	}
	private String getDate() {
		//这个方法属于java中常见的获取当前时间并格式化输出的例子
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//确定时间的格式
		return df.format(new Date());
		//new Date取当前系统时间，使用创建的格式实例格式化返回去到的时间。
	}
}

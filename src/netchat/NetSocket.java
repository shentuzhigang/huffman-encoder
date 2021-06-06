package netchat;
import java.io.*;
import java.net.*;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
public class NetSocket {
	private InetAddress inadd;
	private Socket sk;
	private ServerSocket ssk;
	private boolean isStop;
	private OutputStream out;
	private InputStream in;
	public NetSocket() {
		isStop=false;
		sk=null;
		ssk=null;
		in=null;
		out=null;
		inadd=null;
	}
	
	/**
	 * @param ip ָ������������ null
	 * @param port �˿�
	 * @return �Ƿ�ɹ���������
	 * @throws IOException ����ڴ����׽���ʱ����I / O����
	 */
	public boolean open(String ip,int port) throws IOException{
		inadd=InetAddress.getByName(ip);
		try {
			sk=new Socket(inadd.getHostAddress(), port);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw e;
		} 
		try {
			in=sk.getInputStream();
			out=sk.getOutputStream();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @param b ����
	 * @throws IOException �������I / O����
	 */
	public void write(byte[] b) throws IOException {
		out.write(b);
	}
	/**
	 * @param b ��ȡ���ݵĻ�����
	 * @return ��ȡ�������������ֽ������������û�и�������ݣ���Ϊ�Ѿ���������ĩβ������ -1 
	 * @throws IOException ��������ļ���β������κ�ԭ������������ѹرգ����߷�������I / O�������޷���ȡ��һ���ֽ�
	 */
	public int read(byte[] b) throws IOException {
		if(sk.isClosed()) return -1;
		return in.read(b);
	}
	/**
	 * @throws IOException �������I / O����
	 */
	public void close() throws IOException{
		sk.close();
	}
	/**
	 * @throws IOException �������I / O����
	 */
	public void shutdownOutput() throws IOException{
		sk.shutdownOutput();
	}
	/**
	 * @throws IOException �������I / O����
	 */
	public void shutdownInput() throws IOException{
		sk.shutdownInput();
	}
	/**
	 * @param port �����˿�
	 * @param ta JTextArea����
	 * @throws IOException �������I / O����
	 */
	public void startReceive(int port,JTextArea ta) throws IOException{
		// TODO �Զ����ɵķ������
		ssk = new ServerSocket(port);
		isStop=false;
		ssk.setSoTimeout(5*1000);
		new Thread(()-> {
			while(!isStop) {
				Socket sk=null;
				try {
					sk = ssk.accept();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				new Thread(new ReceiveThread(sk,ta)).start();
			}
			try {
				ssk.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}).start();
	}
	/**
	 * Close ReceiveThread
	 */
	public void stopReceive() {
		// TODO �Զ����ɵķ������
		isStop=true;
	}
}

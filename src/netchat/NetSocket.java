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
	 * @param ip 指定的主机，或 null
	 * @param port 端口
	 * @return 是否成功建立连接
	 * @throws IOException 如果在创建套接字时发生I / O错误
	 */
	public boolean open(String ip,int port) throws IOException{
		inadd=InetAddress.getByName(ip);
		try {
			sk=new Socket(inadd.getHostAddress(), port);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw e;
		} 
		try {
			in=sk.getInputStream();
			out=sk.getOutputStream();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @param b 数据
	 * @throws IOException 如果发生I / O错误
	 */
	public void write(byte[] b) throws IOException {
		out.write(b);
	}
	/**
	 * @param b 读取数据的缓冲区
	 * @return 读取到缓冲区的总字节数，或者如果没有更多的数据，因为已经到达流的末尾，则是 -1 
	 * @throws IOException 如果由于文件结尾以外的任何原因，如果输入流已关闭，或者发生其他I / O错误，则无法读取第一个字节
	 */
	public int read(byte[] b) throws IOException {
		if(sk.isClosed()) return -1;
		return in.read(b);
	}
	/**
	 * @throws IOException 如果发生I / O错误
	 */
	public void close() throws IOException{
		sk.close();
	}
	/**
	 * @throws IOException 如果发生I / O错误
	 */
	public void shutdownOutput() throws IOException{
		sk.shutdownOutput();
	}
	/**
	 * @throws IOException 如果发生I / O错误
	 */
	public void shutdownInput() throws IOException{
		sk.shutdownInput();
	}
	/**
	 * @param port 监听端口
	 * @param ta JTextArea对象
	 * @throws IOException 如果发生I / O错误
	 */
	public void startReceive(int port,JTextArea ta) throws IOException{
		// TODO 自动生成的方法存根
		ssk = new ServerSocket(port);
		isStop=false;
		ssk.setSoTimeout(5*1000);
		new Thread(()-> {
			while(!isStop) {
				Socket sk=null;
				try {
					sk = ssk.accept();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				new Thread(new ReceiveThread(sk,ta)).start();
			}
			try {
				ssk.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}).start();
	}
	/**
	 * Close ReceiveThread
	 */
	public void stopReceive() {
		// TODO 自动生成的方法存根
		isStop=true;
	}
}

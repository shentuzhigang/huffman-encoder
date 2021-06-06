package test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import javax.swing.JTextArea;

import huffman.HuffmanTree;
public class TestWhileClient {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		for(int i=0;i<10;i++)
			new SendTestThread(i).start();
	}

}


class SendTestThread extends Thread {
	int flag;
	public SendTestThread(int flag){
		this.flag=flag;
	}
	public void run() {
		InetAddress inadd=null;
		try {
			inadd = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		Socket sk=null;
		try {
			sk=new Socket(inadd.getHostAddress(), 8889);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		try {
			InputStream  in=sk.getInputStream();
			OutputStream out=sk.getOutputStream();
			int n=10000;
			while(n--!=0) {
				out.write((flag+"100011").getBytes());
				out.flush();
			}
			sk.shutdownOutput();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}


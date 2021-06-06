/**
 * 
 */
package netchat;

import java.io.*;
import java.net.*;

import javax.swing.*;

import huffman.HuffmanTree;

/**
 * @author ShenTuZhiGang
 *
 */
public class ReceiveThread extends Thread {
	Socket s;
	HuffmanTree HTree;
	JTextArea ta;
	public ReceiveThread(Socket s,JTextArea ta) {
		this.s=s;
		this.ta=ta;
		HTree=new HuffmanTree();
		HTree.createHuffmanCode();
	}
	public void run() {
		String ip=s.getInetAddress().getHostAddress();
		try {
			System.out.println(ip+"会话开启");
			InputStream in=s.getInputStream();
			OutputStream out = s.getOutputStream();
			synchronized(ta) {
				out.write(("Connect is OK,My Dear "+ip+".").getBytes());
				ta.append("["+ip+"] is connected."+"\r\n");
			}
			byte[] buf=new byte[1024];
			int len = 0;
			while((len = in.read(buf))!=-1) {
				String str=new String(buf,0,len);
				System.out.println(ip+":"+str+"\r\n");
				String destr=HTree.deCode(str);
				synchronized(ta) {
					if(destr==null)
						ta.append("Oh,My God! "+ip+" send a message but it isn\'t Huffman Code!"+"\r\n");
					else
						ta.append("Remote Host["+ip+"]:"+destr+"\r\n");
				}
			}
			synchronized(ta) {
				out.write(("Connect is Close,My Dear "+ip+".").getBytes());
				ta.append("["+ip+"] is exited."+"\r\n");
			}
			//out.flush();
			s.shutdownInput();
			s.shutdownOutput();
			System.out.println(ip+"会话关闭");
		} catch(IOException e) {
			throw new RuntimeException(ip+":update error");
		} finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}

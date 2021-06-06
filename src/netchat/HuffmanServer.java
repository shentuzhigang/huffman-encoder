/**
 * 
 */
package netchat;

import java.io.*;
import java.net.*;
import huffman.*;
/**
 * @author ShenTuZhiGang
 *
 */
public class HuffmanServer {

	/**
	 * @param args 参数
	 * @throws IOException 如果发生I / O错误
	 */
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8889);
		while(true) {
			Socket sk=ss.accept();
			new Thread(new HuffmanCodeThread(sk)).start();
		}
	}
	/**
	 * @throws IOException 如果发生I / O错误
	 */
	public void start() throws IOException {
		// TODO 自动生成的方法存根
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8889);
		while(true) {
			Socket sk=ss.accept();
			new Thread(new HuffmanCodeThread(sk)).start();
		}
	}
	/**
	 * @throws IOException 如果发生I / O错误
	 */
	public void start(int port) throws IOException {
		// TODO 自动生成的方法存根
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(port);
		while(true) {
			Socket sk=ss.accept();
			new Thread(new HuffmanCodeThread(sk)).start();
		}
	}

}
class HuffmanCodeThread extends Thread{
	Socket s;
	HuffmanTree HTree;
	public HuffmanCodeThread(Socket s) {
		this.s=s;
		HTree=new HuffmanTree();
		HTree.createHuffmanCode();
	}
	public void run() {
		String ip=s.getInetAddress().getHostAddress();
		try {
			print(ip+"会话开启");
			InputStream in=s.getInputStream();
			OutputStream out = s.getOutputStream();
			synchronized(HTree) {
				out.write(("Connect is OK,my dear "+ip).getBytes());
			}
			byte[] buf=new byte[1024];
			int len = 0;
			while((len = in.read(buf))!=-1) {
				String str=new String(buf,0,len);
				print(ip+":"+str+"\r\n");
				String destr=HTree.deCode(str);
				synchronized(HTree) {
					if(destr==null)
						out.write("Oh,My God! This isn\'t Huffman Code!".getBytes());
					else
						out.write(destr.getBytes());
				}
			}
			/*
			BufferedReader bfr =new BufferedReader(new InputStreamReader(in)) ;
			BufferedWriter bfw =new BufferedWriter(new OutputStreamWriter(out)) ;
			String str=null;
			while((str = bfr.readLine())!=null) {
				print(ip+":"+str+"\r\n");
				String destr=HTree.deCode(str);
				synchronized(HTree) {
					if(destr==null)
						bfw.write("Oh,My God! This isn\'t Huffman Code!");
					else
						bfw.write(destr);
				}
			}
			synchronized(HTree) {
				bfw.write("Connect is Close,my dear "+ip);
			}
			*/
			synchronized(HTree) {
				out.write(("Connect is Close,my dear "+ip).getBytes());
			}
			//out.flush();
			s.shutdownInput();
			s.shutdownOutput();
			print(ip+"会话关闭");
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
	public static void print(Object obj) {
		System.out.println(obj);
	}
}
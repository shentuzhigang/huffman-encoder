/**
 * 
 */
package huffman;

import java.awt.Graphics;
import java.util.*;

import javax.swing.text.JTextComponent;

/**
 * @author ShenTuZhiGang
 * @version 1.0
 * @since 1.0
 */
public class HuffmanTree {
	private HTNode[] HT=null; 
	private final int MAX_INDEX=128;
	private char[] code;
	private double[] weight;
	private int num;
	private int node_tot;
	private String[] HuffmanCodeList;
	private static final char[] default_code= {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N',
	                                   		   'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final double[] default_weight= {186,64,13,22,32,103,21,15,47,57,1,5,32,20,57,63,15,1,48,51,80,23,8,18,1,16,1};	
	private static final int default_num=27;
	/**
	 * 
	 */
	public HuffmanTree() {
		create();
	}
	/**
	 * @param code - 字符集
	 * @param weight - 频数
	 */
	public HuffmanTree(char[] code,double[] weight) {
		create(code,weight);
	}
	/**
	 * @return 字符集
	 */
	public char[] getCode() {
		return code;
	}
	/**
	 * @return 频率
	 */
	public double[] getWeight() {
		return weight;
	}
	/**
	 * @return 字符总数
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @return 节点总数
	 */
	public int getNode_tot() {
		return node_tot;
	}
	/**
	 * @return Huffman Code List
	 */
	public String[] getHuffmanCodeList() {
		return HuffmanCodeList;
	}
	public void create() {
		// TODO 自动生成的方法存根
		create(default_code,default_weight);
	}
	/**
	 * @param code - 字符集
	 * @param weight - 频数
	 */
	public void create(char[] code,double[] weight) {
		num=code.length;	//获取字符集字符总数
		node_tot=2*num-1;	//总共需要2num－1个节点
		PriorityQueue<HTNode> Q = new PriorityQueue<HTNode>();	//优先队列排序
		HT=new HTNode[node_tot+1];
		HT[0]=new HTNode(0);
		//给叶子结点赋值
		for(int i=1;i<=num;i++) {
			HT[i]=new HTNode(i,code[i-1],weight[i-1]);
			Q.add(HT[i]);
		}
		int id=num;
		//Huffman编码
		while(Q.size()>=2) {
			//找到两个权值最小的结点作为左右子树的根节点构造新的二叉树。
			HTNode s1=Q.remove();
			HTNode s2=Q.remove();
			id++;
			//创建新的节点
			//新节点的权值是两个子节点之和
			HT[id]=new HTNode(id,s1,s2);		
			s1.parent=s2.parent=id;		//将两个子节点的父节点设置为 id;
			//新节点重新放入队列
			Q.add(HT[id]);
		}
	}
	/**
	 * 从叶子到根逆向求每个字符的Huffman编码
	 */
	public void createHuffmanCode() {
		int start,c,f;
		char[] cd=new char[num];
		HuffmanCodeList=new String[num+1];	//分配内存空间
		//依次对所有字符编码
		for(int i=1; i<=num; i++){
	        start=num;
	        //从叶子到根逆向求编码
	        for(c=i,f=HT[i].parent;f!=-1;c=f,f=HT[f].parent) {
	        	 if(HT[f].lchild==c) {
	                 cd[--start]='0';
	             }else{
	                 cd[--start]='1';
	             }
	        }
	        //复制编码到Huffman编码表
	        HuffmanCodeList[i]=new String(cd,start,num-start);
	    }
	}
	/**
	 * @return HuffmanTreeString - HuffmanTree的字符串显示形式（括号法）
	 */
	@Override 
	public String toString() {
		return super.toString()+":[size="+node_tot+","+toBrackets(node_tot)+"]";
	}
	/**
	 * @param code_str - 需要编码的字符串
	 * @return encode - 完成Huffman编码的字符串
	 */
	public String enCode(String code_str) {
		int len=code_str.length();
		String encode=new String();
		int Flag=0;//匹配标记
		//依次与所有字符编码开始匹配
		for(int i=0;i<len;i++) {
			Flag=0;
			for(int j=1;j<=num;j++) {
				//匹配成功
				if(code_str.charAt(i)==HT[j].code ) {
					encode=encode+HuffmanCodeList[j];
					Flag=1;
				}
			}
			if(Flag==0)return null;
		}
		return encode;
	}
	/**
	 * @param code_str - 需要译码的字符串
	 * @return encode - 完成Huffman译码的字符串
	 */
	public String deCode(String code_str) {
		int len=code_str.length();
		String decode=new String();
		String temp=new String();
		//依次与所有字符编码开始匹配
		for(int i=0;i<len;i++) {
			temp=temp+code_str.charAt(i);
			for(int j=1;j<=num;j++) {
				//匹配成功
				if(HuffmanCodeList[j].equals(temp)) {
					decode=decode+HT[j].code;
					temp="";
					break;
				}
			}
			if(temp.length()>128)return null;
		}
		if(!temp.equals(""))return null;
		return decode;
	}
	/**
	 * 输出每个字符的Huffman编码
	 */
	public void printHuffmanCodeList() {
		print("字符集总数："+num);
		for(int i=1;i<=num;i++) {
			 print("\'"+HT[i].code+"\':"+HuffmanCodeList[i]);
		}
	}
	/**
	 * 调用{@link huffman.HuffmanTree#toBrackets(int) toBrackets(int root)}方法，生成HuffmanTree的括号法表示形式
	 * 括号法显示HuffmanTree
	 * @return resstr - HuffmanTree的括号法表示形式
	 */
	public String printHuffmanTree() {
		return toBrackets(node_tot);
	}
	/**
	 *  括号法显示HuffmanTree
	 * 递归法
	 * @param root - HuffmanTree ROOT
	 * @return resstr - HuffmanTree的括号法表示形式
	 */
	public String toBrackets(int root) {
		String resstr="";
		if(root!=-1){
	        resstr+=(HT[root].code=='\0')?HT[root].weight:HT[root].code;
	        if(HT[root].lchild!=-1||HT[root].rchild!=-1){
	        	resstr+="("+toBrackets(HT[root].lchild)+","+toBrackets(HT[root].rchild)+")";
	        }
	    }
		return resstr;
	}
	/**
	 * @return HuffmanCodeList String
	 */
	public String HuffmanCodeListString() {
		String res="字符集总数："+num+"\r\n";
		for(int i=1;i<=num;i++) {
			res+="\'"+chartoString(HT[i].code)+"\':"+HuffmanCodeList[i]+"\r\n";
		}
		return res;
	}
	/**
	 * @return HuffmanList String
	 */
	public String HuffmanListString() {
		String res="node\t|weight\t|parent\t|lchild\t|rchild\t|\r\n";
		for(int i=1;i<=node_tot;i++) {
			res+=HT[i].id+"\t|"+HT[i].weight+"\t|"+HT[i].parent+"\t|"+HT[i].lchild+"\t|"+HT[i].rchild+"\t|\r\n";
		}
		return res;
	}
	/**
	 * @param root HuffmanTree ROOT
	 * @param g Graphics
	 *
	 */
	public void paintTree(int root,Graphics g,int x,int y){
		if(root!=-1){
	        if(HT[root].code=='\0')
	        	g.drawString(new Double(HT[root].weight).toString(),x-10,y+10);
	        else
	        	g.drawString(chartoString(HT[root].code)+(new Double(HT[root].weight).toString()),x-10,y+10);
	        if(HT[root].lchild!=-1||HT[root].rchild!=-1){
	        	int width=7*HT[root].leaf;
	        	g.drawLine(x, y+10, x-width, y+30);
	        	paintTree(HT[root].lchild, g,x-width,y+30);
	        	g.drawLine(x, y+10, x+width, y+30);
	        	paintTree(HT[root].rchild, g,x+width,y+30);
	        }
	    }
	}   
	private String chartoString(char ch) {
		if(ch=='\n')
			return "\\n";
		else if(ch=='\r')
			return "\\r";
		else
			return ch+"";
	}
	public static void print(Object obj) {
		System.out.println(obj);
	}
}

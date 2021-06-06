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
	 * @param code - �ַ���
	 * @param weight - Ƶ��
	 */
	public HuffmanTree(char[] code,double[] weight) {
		create(code,weight);
	}
	/**
	 * @return �ַ���
	 */
	public char[] getCode() {
		return code;
	}
	/**
	 * @return Ƶ��
	 */
	public double[] getWeight() {
		return weight;
	}
	/**
	 * @return �ַ�����
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @return �ڵ�����
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
		// TODO �Զ����ɵķ������
		create(default_code,default_weight);
	}
	/**
	 * @param code - �ַ���
	 * @param weight - Ƶ��
	 */
	public void create(char[] code,double[] weight) {
		num=code.length;	//��ȡ�ַ����ַ�����
		node_tot=2*num-1;	//�ܹ���Ҫ2num��1���ڵ�
		PriorityQueue<HTNode> Q = new PriorityQueue<HTNode>();	//���ȶ�������
		HT=new HTNode[node_tot+1];
		HT[0]=new HTNode(0);
		//��Ҷ�ӽ�㸳ֵ
		for(int i=1;i<=num;i++) {
			HT[i]=new HTNode(i,code[i-1],weight[i-1]);
			Q.add(HT[i]);
		}
		int id=num;
		//Huffman����
		while(Q.size()>=2) {
			//�ҵ�����Ȩֵ��С�Ľ����Ϊ���������ĸ��ڵ㹹���µĶ�������
			HTNode s1=Q.remove();
			HTNode s2=Q.remove();
			id++;
			//�����µĽڵ�
			//�½ڵ��Ȩֵ�������ӽڵ�֮��
			HT[id]=new HTNode(id,s1,s2);		
			s1.parent=s2.parent=id;		//�������ӽڵ�ĸ��ڵ�����Ϊ id;
			//�½ڵ����·������
			Q.add(HT[id]);
		}
	}
	/**
	 * ��Ҷ�ӵ���������ÿ���ַ���Huffman����
	 */
	public void createHuffmanCode() {
		int start,c,f;
		char[] cd=new char[num];
		HuffmanCodeList=new String[num+1];	//�����ڴ�ռ�
		//���ζ������ַ�����
		for(int i=1; i<=num; i++){
	        start=num;
	        //��Ҷ�ӵ������������
	        for(c=i,f=HT[i].parent;f!=-1;c=f,f=HT[f].parent) {
	        	 if(HT[f].lchild==c) {
	                 cd[--start]='0';
	             }else{
	                 cd[--start]='1';
	             }
	        }
	        //���Ʊ��뵽Huffman�����
	        HuffmanCodeList[i]=new String(cd,start,num-start);
	    }
	}
	/**
	 * @return HuffmanTreeString - HuffmanTree���ַ�����ʾ��ʽ�����ŷ���
	 */
	@Override 
	public String toString() {
		return super.toString()+":[size="+node_tot+","+toBrackets(node_tot)+"]";
	}
	/**
	 * @param code_str - ��Ҫ������ַ���
	 * @return encode - ���Huffman������ַ���
	 */
	public String enCode(String code_str) {
		int len=code_str.length();
		String encode=new String();
		int Flag=0;//ƥ����
		//�����������ַ����뿪ʼƥ��
		for(int i=0;i<len;i++) {
			Flag=0;
			for(int j=1;j<=num;j++) {
				//ƥ��ɹ�
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
	 * @param code_str - ��Ҫ������ַ���
	 * @return encode - ���Huffman������ַ���
	 */
	public String deCode(String code_str) {
		int len=code_str.length();
		String decode=new String();
		String temp=new String();
		//�����������ַ����뿪ʼƥ��
		for(int i=0;i<len;i++) {
			temp=temp+code_str.charAt(i);
			for(int j=1;j<=num;j++) {
				//ƥ��ɹ�
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
	 * ���ÿ���ַ���Huffman����
	 */
	public void printHuffmanCodeList() {
		print("�ַ���������"+num);
		for(int i=1;i<=num;i++) {
			 print("\'"+HT[i].code+"\':"+HuffmanCodeList[i]);
		}
	}
	/**
	 * ����{@link huffman.HuffmanTree#toBrackets(int) toBrackets(int root)}����������HuffmanTree�����ŷ���ʾ��ʽ
	 * ���ŷ���ʾHuffmanTree
	 * @return resstr - HuffmanTree�����ŷ���ʾ��ʽ
	 */
	public String printHuffmanTree() {
		return toBrackets(node_tot);
	}
	/**
	 *  ���ŷ���ʾHuffmanTree
	 * �ݹ鷨
	 * @param root - HuffmanTree ROOT
	 * @return resstr - HuffmanTree�����ŷ���ʾ��ʽ
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
		String res="�ַ���������"+num+"\r\n";
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

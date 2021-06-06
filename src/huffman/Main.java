/**
 * 
 */
package huffman;

/**
 * @author ShenTuZhiGang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		HuffmanTree MyHT=new HuffmanTree();
		MyHT.createHuffmanCode();
		MyHT.printHuffmanCodeList();
		print(MyHT.enCode("THIS PROGRAME  IS  MY  FAVORITE"));
		print(MyHT.deCode("1101000101100011111100001001010011000100010101011001001011111101100011111111110010100011111111110011101011000001001001001101101010"));
		print(MyHT.toString());
		print(MyHT.printHuffmanTree());
		print(MyHT.HuffmanListString());	
	}
	public static void print(Object obj) {
		System.out.println(obj);
	}
}

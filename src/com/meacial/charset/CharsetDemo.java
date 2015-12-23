package com.meacial.charset;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 22, 2015
 * 
 */
public class CharsetDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		// 字符串
		String a = "你好"; // 内存中存储为 \u4f60\u597d
		// a = "\u4f60\u597d";
//		String a_uncode = "\u4f60\u597d";
//		System.out.println(a == a_uncode);
		
		
		// 将unicode字符按UTF-8,GBK编码
//		byte[] a_utf = a.getBytes("utf-8");
//		byte[] a_gbk = a.getBytes("gbk");
//		System.out.println(Arrays.toString(a_utf));
//		System.out.println(Arrays.toString(a_gbk));
//		
//		// 解码
//		String a_utf_decod = new String(a_utf, "utf-8");
//		String a_gbk_decod = new String(a_gbk, "gbk");
//		System.out.println(a_utf_decod);
//		System.out.println(a_gbk_decod);
//		
//		System.out.println("\uFFFD");
//		
//		Byte b = new Byte((byte) 0);
//		System.out.println(b);
//		System.out.println("严".getBytes().length);
//		System.out.println("中");
//		System.out.println("你好".getBytes("UTF-8").length);
//		//System.out.println(new String("\u00aa".getBytes("gbk")));
//		
//		System.out.println(new String((new String("你好".getBytes("UTF-8"),"ISO8859-1")).getBytes(),"UTF-8"));
//		
//		
		char[] car = Character.toChars(0x8054); // 将 编码point 转换为对应的字符
		System.out.println(new String(car));
		System.out.println(car.length);
		
		System.out.println(new String("\u00c1\u00aa".getBytes("ISO8859-1"), "gbk"));
		
		
		
//		System.out.println(new String(car));
//		char[] sup_char = Character.toChars(0x21000);
//		System.out.println(sup_char.length);
//		System.out.println(new String(sup_char));
//		
//		
//		System.out.println(getUnicodeStr("严"));
//		
//		char[] char_a = new char[]{'联','动'};
//		System.out.println(Character.MAX_CODE_POINT);
//		System.out.println(Character.MAX_VALUE);
//		System.out.println(char_a[0]);
//		
//		System.out.println(new String(char_a));
//		
//		System.out.println("中国".toCharArray().length);
//		System.out.println("𡀀".toCharArray().length);
//		System.out.println("𡀀".length() +","+"𡀀".toCharArray().length);
//		System.err.println("a".toCharArray().length);
//		System.err.println("a".getBytes("UNICODE").length); // UNICODE用4个字节标识一个字符
//		System.out.println("中".getBytes("UTF-8").length);
//		
//		String iso = "\u00c1\u00aa\u00b6\u00af\u00d3\u00c5\u00ca\u00c6\u00b5\u00e7\u00d7\u00d3\u00c9\u00cc\u00ce\u00f1\u00d3\u00d0\u00cf\u00de\u00b9\u00ab\u00cb\u00be";
//		System.out.println(iso.toCharArray().length);
//		System.out.println(iso.toCharArray());
//		
//		char[] ssss = new char[2];
//		System.arraycopy(iso.toCharArray(), 0, ssss, 0, 2);
//		System.out.println(new String(ssss));
//		
		
		
//		String iso_code = "\u00c1\u00aa\u00b6\u00af\u00d3\u00c5\u00ca\u00c6";
//		String gbk_code = "\u8054\u52a8\u4f18\u52bf";
//		String utf_code = "\ufffd\u0439\ufffd";
//		
//		// System.out.println(iso_code.getBytes(""));
//		System.out.println(new String(iso_code.getBytes("ISO8859-1"), "GBK"));
//		System.out.println(new String(utf_code.getBytes("ISO8859-1"), "UTF-8"));
//		System.out.println(new String(utf_code.getBytes("utf-8"), "gbk"));
		
		
	}
	
	
	private static final String getUnicodeStr(String a) {
		String temp = "";
		
		for (int i = 0; i < a.length(); i++) {
			temp += "\\u"+Integer.toHexString(a.charAt(i));
		}
		return temp;
	}
	
	public static boolean isBmpCodePoint(int codePoint) {
        return codePoint >>> 16 == 0; // 二进制无符号右移 16位，正好是2个字节
    }
}

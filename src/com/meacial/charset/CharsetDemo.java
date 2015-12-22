package com.meacial.charset;

import java.io.UnsupportedEncodingException;
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
		byte[] a_utf = a.getBytes("utf-8");
		byte[] a_gbk = a.getBytes("gbk");
		System.out.println(Arrays.toString(a_utf));
		System.out.println(Arrays.toString(a_gbk));
		
		// 解码
		String a_utf_decod = new String(a_utf, "utf-8");
		String a_gbk_decod = new String(a_gbk, "gbk");
		System.out.println(a_utf_decod);
		System.out.println(a_gbk_decod);
		
		System.out.println("\uFFFD");
		
		Byte b = new Byte((byte) 0);
		System.out.println(b);
		System.out.println("严".getBytes().length);
		System.out.println("中");
		System.out.println("你好".getBytes("UTF-8").length);
		//System.out.println(new String("\u00aa".getBytes("gbk")));
		
		System.out.println(new String((new String("你好".getBytes("UTF-8"),"ISO8859-1")).getBytes(),"UTF-8"));
	}
}

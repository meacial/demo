package com.meacial.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 23, 2015
 * 
 */
public class EncUtil {
	
	static char[] hexs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };

	private static String toHex(byte b) {
		String s = "";
		s += hexs[(b >> 4) & 0xf];
		s += hexs[b & 0xf];
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset());
		String a = "联";
		byte[] b = null;
		try {
			b = a.getBytes("UNICODE");
			// b = a.getBytes("ISO8859-1");
			// b = a.getBytes("GBK");
			// b = a.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < b.length; i++) {
			System.out.print(toHex(b[i]));
		}
	}
}

package com.meacial.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/** 
 * </pre>指定编码读取文件，防止读取文件乱码的情况
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class FileRead {

	public static void main(String[] args) throws IOException {
		File toRead =  new File("a.txt");
		FileInputStream fis = new FileInputStream(toRead);
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		BufferedReader br = new BufferedReader(isr, 1024);
		while(true) {
			br.readLine();
		}
	}
}

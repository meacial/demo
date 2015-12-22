package com.meacial.charset;

/**
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 22, 2015
 * 
 */
public class Test_Iso8859_1 {
	public static String s =   
	        "http://baike.baidu.com/view/2613676.htm\r\n" +  
	        "　　ISO-8859-1编码是单字节编码，向下兼容ASCII，其编码范围是0x00-0xFF，" +  
	        "0x00-0x7F之间完全和ASCII一致，0x80-0x9F之间是控制字符，0xA0-0xFF之间是文字符号。" +  
	        "ISO-8859-1收录的字符除ASCII收录的字符外，还包括西欧语言、希腊语、泰语、阿拉伯语、希伯来语" +  
	        "对应的文字符号。欧元符号出现的比较晚，没有被收录在ISO-8859-1当中。\r\n" +  
	        "　　因为ISO-8859-1编码范围使用了单字节内的所有空间，在支持ISO-8859-1的系统中传输和存储其他任何" +  
	        "编码的字节流都不会被抛弃。换言之，把其他任何编码的字节流当作ISO-8859-1编码看待都没有问题。这是" +  
	        "个很重要的特性，MySQL数据库默认编码是Latin1就是利用了这个特性。ASCII编码是一个7位的容器，" +  
	        "ISO-8859-1编码是一个8位的容器。\r\n" +  
	        "　　Latin1是ISO-8859-1的别名，有些环境下写作Latin-1。";  
	  
	    public static void testChar(char c) throws Exception  
	    {  
	        //char c = '中';  
	        System.out.println( c );  
	        System.out.println( (int)c );  
	        System.out.println( Integer.toHexString(c) );  
	        if(c>255)  
	        {  
	            byte low = (byte)(c / 256);  
	            byte hight = (byte)(c % 256);  
	            System.out.println( getHex(low) );  
	            System.out.println( getHex(hight) );  
	        }  
	    }  
	      
	    public static void testString() throws Exception  
	    {  
	        for(int i=0; i<s.length(); i++)  
	        {  
	            testChar(s.charAt(i));  
	        }  
	    }  
	      
	  
	    public static void testString_02() throws Exception  
	    {  
	          
	        StringBuffer sb = new StringBuffer();  
	        for(int i=0; i<s.length(); i++)  
	        {  
	            char c = s.charAt(i);  
	            if(c>255)  
	            {  
	                char hight = (char)(c / 256);  
	                char low = (char)(c % 256);  
	                sb.append(hight);  
	                sb.append(low);  
	            }  
	            else  
	            {  
	                sb.append(c);  
	            }  
	        }  
	        String sIso88591 = sb.toString();  
	        System.out.println(sIso88591);  
	          
	        byte[] buf = sIso88591.getBytes("iso-8859-1");  
	        showBytes(buf);  
	        String sUtf_8 = new String(buf, "gbk");  
	        System.out.println(sUtf_8);  
	    }  
	      
	    public static void testString_03() throws Exception  
	    {  
	        System.out.println("s:" + s);  
	          
	        byte[] buf_gbk = s.getBytes("gbk");  
	        System.out.println("buf_gbk:");  
	        showBytes(buf_gbk);  
	          
	        StringBuffer sb = new StringBuffer();  
	        for(int i=0; i<buf_gbk.length; i++)  
	        {  
	            char c = (char)buf_gbk[i];  
	            sb.append( c );  
	            System.out.println(c + ":" + getHex((byte)c));  
	        }  
	          
	        String sIso88591 = sb.toString();  
	        System.out.println("sIso88591:");  
	        System.out.println(sIso88591);  
	        System.out.println("showChar(sIso88591):");  
	        showChar(sIso88591);  
	          
	        byte[] buf_iso88591 = sIso88591.getBytes("iso-8859-1");  
	        System.out.println("buf_iso88591:");  
	        showBytes(buf_iso88591);  
	        String sUtf_8 = new String(buf_iso88591, "gbk");  
	        System.out.println(sUtf_8);  
	  
	    }  
	      
	    public static void testString_04() throws Exception  
	    {  
	        System.out.println("s:" + s);  
	          
	        byte[] buf_gbk = s.getBytes("gbk");  
	        System.out.println("buf_gbk:");  
	        showBytes(buf_gbk);  
	          
	        String sIso88591 = new String(buf_gbk, "iso-8859-1");  
	          
	        System.out.println("sIso88591:");  
	        System.out.println(sIso88591);  
	        System.out.println("showChar(sIso88591):");  
	        showChar(sIso88591);  
	          
	        byte[] buf_iso88591 = sIso88591.getBytes("iso-8859-1");  
	        System.out.println("buf_iso88591:");  
	        showBytes(buf_iso88591);  
	        String sGbk = new String(buf_iso88591, "gbk");  
	        System.out.println(sGbk);  
	  
	    }  
	      
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) throws Exception{  
	        // testString();  
	    	// testString_02();  
	    	testString_04();  
	    }  
	    
	    public static String getHex(byte b)  
	    {  
	        String hex = Integer.toHexString(b & 0xff);  
	        if(hex.length()==1)  
	        {  
	            hex = "0" + hex;  
	        }  
	        return hex;  
	    }  
	  
	    public static void showBytes(byte[] buffer)  
	    {  
	        for(int i=0; i<buffer.length; i++)  
	        {  
	            System.out.print( getHex(buffer[i]) + " ");  
	        }  
	        System.out.println();  
	  
	    }  
	  
	    public static void showChar(String s)  
	    {  
	        for(int i=0; i<s.length(); i++)  
	        {  
	            System.out.print( getHex((byte)(s.charAt(i))) + " ");  
	        }  
	        System.out.println();  
	  
	    }  
}

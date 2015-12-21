package com.meacial.jdk1_7;

/**</pre>带资源的try语句
 * 
 * JDK1.7之前，try中关闭一个资源，如果关闭出现异常，再交给finally块去关闭，如果finally关闭时，出现异常，该异常则会抛出，这样的话就覆盖了原始的异常。
 * 
 * 在jdk 1.7之后出现了带资源的try语句，它允许在try关键字后紧跟一对圆括号，圆括号可以声明、初始化一个或多个资源（此处的资源是指那些必须在程序结束时显式关闭的资源，
 * 比如数据库连接，网络连接等），try-with-resources 是一个定义了一个或多个资源的try 声明，try语句在该语句结束时自动关闭这些资源。
 * try-with-resources确保每一个资源在处理完成后都会被关闭。这些资源必须实现AutoCloseable或者Closeable接口，实现这两个接口就必须实现close() 方法。
 * 示例如下：
 * 
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class TryBlockWithResource {

	public static void main(String[] args) {
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

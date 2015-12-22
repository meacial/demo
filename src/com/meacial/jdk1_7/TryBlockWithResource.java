package com.meacial.jdk1_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**</pre>带资源的try语句
 * 
 * JDK1.7之前，try中关闭一个资源，如果关闭出现异常，再交给finally块去关闭，如果finally关闭时，出现异常，该异常则会抛出，这样的话就覆盖了原始的异常。
 * 
 * 在jdk 1.7之后出现了带资源的try语句，它允许在try关键字后紧跟一对圆括号，圆括号可以声明、初始化一个或多个资源（此处的资源是指那些必须在程序结束时显式关闭的资源，
 * 比如数据库连接，网络连接等），try-with-resources 是一个定义了一个或多个资源的try 声明，try语句在该语句结束时自动关闭这些资源。
 * try-with-resources确保每一个资源在处理完成后都会被关闭。这些资源必须实现AutoCloseable或者Closeable接口，实现这两个接口就必须实现close() 方法。
 * 示例如下：
 *
 * 一个try-with-resourcse声明了包含两个对象的声明，用分号隔开（某些书里错写成了逗号）。和声明一个对象相同，会在结束后自动调用close方法。 此外，try-with-resources 可以跟catch和finally，catch和finally中的对象是在try-with-resources里声明的对象关闭之后才执行（并不建议这样）。如果try块和close块都抛出一个异常，那么close块的异常将会“被抑制”。这些异常将会自动捕获，并由addSuppressed方法增加到原来的异常。如果对这个异常感兴趣，可以调用getSuppressed方法，将会得到从close方法抛出并被抑制的异常列表。

        jdk1.7之后新增加了几个API：

        1）void addSuppressed(Throwable t)  为这个异常增加一个“抑制”异常。这出现在带资源的try语句块中，其中t是close方法抛出的一个异常。

        2）Throwable[ ] getSuppressed( )   得到这个异常的所有“抑制”异常。一般来说，这些是带资源的try语句中close方法抛出的异常。
 * 
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class TryBlockWithResource {
	public static void main(String[] args) throws FileNotFoundException {
		
		try(Scanner in = new Scanner(new File("in.file"));PrintWriter out = new PrintWriter(new File("out.file"))) {
			while(in.hasNext())
				out.println(in.next().toUpperCase());
		}
		}
}

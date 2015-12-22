package com.meacial.socket.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * </pre>DISCARD服务(丢弃服务，指的是会忽略所有接收的数据的一种协议)
 * Handles a server-side channel
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// Discard the recived message silently.
		((ByteBuf) msg).release(); // 为了测试服务器能正常接受数据，我们将这里注释掉

		
		/*
		 * ByteBuf是一个引用计数对象，这个对象必须显示的调用release()方法。
		 * 通常，channelRead()方法的实现就像下面的这段代码：
		 *  
			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) {
			    try {
			        // Do something with msg
			    } finally {
			        ReferenceCountUtil.release(msg);
			    }
			}
		* 
		*/
		
		// 为了测试服务器能正常接受数据，我们这里坐下调整
//		ByteBuf in = (ByteBuf) msg;
//		try {
//			while(in.isReadable()) {
//				System.out.println((char)in.readByte());
//				System.out.flush();
//			}
//		} finally {
//			ReferenceCountUtil.release(msg);
//		}
 
	}
	
	/**
	 * exceptionCaught()事件处理方法是当出现Throwable对象才会被调用，即当Netty由于IO错误或者处理器在处理事件时抛出的异常时。
	 * 在大部分情况下，捕获的异常应该被记录下来并且把关联的channel给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
	 * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// Close the connection when an exception is rised.
		cause.printStackTrace();
		ctx.close();
	}
}

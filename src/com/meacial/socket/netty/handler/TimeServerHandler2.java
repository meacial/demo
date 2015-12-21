package com.meacial.socket.netty.handler;

import java.sql.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 流数据的传输处理
 * 一个小的Socket Buffer问题
 * 在基于流的传输里比如TCP/IP，接收到的数据会先被存储到一个socket接收缓冲里。
 * 不幸的是，基于流的传输并不是一个数据包队列，而是一个字节队列。即使你发送了2个独立的数据包，操作系统也不会作为2个消息处理而仅仅是作为一连串的字节而言。
 * 因此这是不能保证你远程写入的数据就会准确地读取。举个例子，让我们假设操作系统的TCP/TP协议栈已经接收了3个数据包：
 * 由于基于流传输的协议的这种普通的性质，在你的应用程序里读取数据的时候会有很高的可能性被分成下面的片段。
 * 因此，一个接收方不管他是客户端还是服务端，都应该把接收到的数据整理成一个或者多个更有意思并且能够让程序的业务逻辑更好理解的数据。
 * 在上面的例子中，接收到的数据应该被构造成下面的格式：
 * 
 * </pre>
 * <h1>第一个解决方案</h1>
 * 现在让我们回到TIME客户端的例子上。这里我们遇到了同样的问题，一个32字节数据是非常小的数据量，他并不见得会被经常拆分到到不同的数据段内。
 * 然而，问题是他确实可能会被拆分到不同的数据段内，并且拆分的可能性会随着通信量的增加而增加。
 * 最简单的方案是构造一个内部的可积累的缓冲，直到4个字节全部接收到了内部缓冲。下面的代码修改了TimeClientHandler的实现类修复了这个问题
 * 
 * ChannelHandler有2个生命周期的监听方法：handlerAdded()和handlerRemoved()。你可以完成任意初始化任务只要他不会被阻塞很长的时间。
 * 首先，所有接收的数据都应该被累积在buf变量里。
 * 然后，处理器必须检查buf变量是否有足够的数据，在这个例子中是4个字节，然后处理实际的业务逻辑。否则，Netty会重复调用channelRead()
 * 当有更多数据到达直到4个字节的数据被积累。
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class TimeServerHandler2 extends ChannelHandlerAdapter {
	
	private ByteBuf buf;
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(4);
	}
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#handlerRemoved(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buf.release();
		buf = null;
	}
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf m = (ByteBuf) msg;
		buf.writeBytes(m);
		m.release();
		
		if (buf.readableBytes() == 4) {
			long currentMill = (buf.readInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentMill));
			ctx.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

package com.meacial.socket.netty.client;

import com.meacial.socket.netty.handler.TimeServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**</pre>Netty client.
 * 
 * 	不像DISCARD和ECHO的服务端，对于TIME协议我们需要一个客户端因为人们不能把一个32位的二进制数据翻译成一个日期或者日历。
 * 	在这一部分，我们将会讨论如何确保服务端是正常工作的，并且学习怎样用Netty编写一个客户端。

	在Netty中,编写服务端和客户端最大的并且唯一不同的使用了不同的BootStrap和Channel的实现。请看一下下面的代码：
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class TimeClient {
	
	
	/**
	 * 	BootStrap和ServerBootstrap类似,不过他是对非服务端的channel而言，比如客户端或者无连接传输模式的channel。
		如果你只指定了一个EventLoopGroup，那他就会即作为一个‘boss’线程，也会作为一个‘workder’线程，尽管客户端不需要使用到‘boss’线程。
		代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel被创建时使用。
		不像在使用ServerBootstrap时需要用childOption()方法，因为客户端的SocketChannel没有父channel的概念。
		我们用connect()方法代替了bind()方法。
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		String host = "localhost";
		
		int port = 7008;
		
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			
			Bootstrap b = new Bootstrap();
			
			b.group(workGroup);
			
			b.channel(NioSocketChannel.class);
			
			b.option(ChannelOption.SO_KEEPALIVE, true);
			
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeServerHandler());
				}
			});
			
			// start the client.
			ChannelFuture f = b.connect(host, port).sync();
			
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
			
		} finally {
			workGroup.shutdownGracefully();
		}
		
	}
}

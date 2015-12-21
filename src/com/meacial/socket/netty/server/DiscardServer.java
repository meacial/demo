package com.meacial.socket.netty.server;

import com.meacial.socket.netty.handler.DiscardServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**</pre>The server with discardhander.
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class DiscardServer {

	private int port;
	
	public DiscardServer(int _port) {
		this.port = _port;
	}
	
	public void run () throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			
			b.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(new DiscardServerHandler());
				}
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			// Bind and start to accept incoming connections
			ChannelFuture f = b.bind(port).sync();
			
			// Wait until the server socket is closed.
			// In this example,this does not happen, but you can do that to gracefully shut down your server.
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		int port = 7008;
		
		new DiscardServer(port).run();
		
	}
}

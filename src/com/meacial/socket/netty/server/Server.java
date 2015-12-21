package com.meacial.socket.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.meacial.socket.netty.handler.DiscardServerHandler;
import com.meacial.socket.netty.handler.EchoServerHander;
import com.meacial.socket.netty.handler.TimeServerHandler;

/**</pre>The server with discardhander.
 * 
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class Server {

	private int port;
	
	public Server(int _port) {
		this.port = _port;
	}
	
	
	/*
	 * 	NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器，Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。在这个例子中我们实现了一个服务端的应用，因此会有2个NioEventLoopGroup会被使用。第一个经常被叫做‘boss’，用来接收进来的连接。第二个经常被叫做‘worker’，用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，并且可以通过构造函数来配置他们的关系。
		ServerBootstrap 是一个启动NIO服务的辅助启动类。你可以在这个服务中直接使用Channel，但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
		这里我们指定使用NioServerSocketChannel类来举例说明一个新的Channel如何接收进来的连接。
		这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel。也许你想通过增加一些处理类比如DiscardServerHandle来配置一个新的Channel或者其对应的ChannelPipeline来实现你的网络程序。当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，然后提取这些匿名类到最顶层的类上。
		你可以设置这里指定的通道实现的配置参数。我们正在写一个TCP/IP的服务端，因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive。请参考ChannelOption和详细的ChannelConfig实现的接口文档以此可以对ChannelOptions的有一个大概的认识。
		你关注过option()和childOption()吗？option()是提供给NioServerSocketChannel用来接收进来的连接。childOption()是提供给由父管道ServerChannel接收到的连接，在这个例子中也是NioServerSocketChannel。
		我们继续，剩下的就是绑定端口然后启动服务。这里我们在机器上绑定了机器所有网卡上的8080端口。当然现在你可以多次调用bind()方法(基于不同绑定地址)。
	 */
	public void run (final ChannelHandler handler) throws Exception {
		
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
					//ch.pipeline().addLast(new DiscardServerHandler());
					ch.pipeline().addLast(handler);
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
		
		// new Server(port).run(new DiscardServerHandler());
		// new Server(port).run(new EchoServerHander());
		new Server(port).run(new TimeServerHandler());
	}
}

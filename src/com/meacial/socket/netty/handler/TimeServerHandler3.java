package com.meacial.socket.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;

/**</pre>
 * 
 * 第二个解决方案
 * 尽管第一个解决方案已经解决了Time客户端的问题了，但是修改后的处理器看起来不那么的简洁，想象一下如果由多个字段比如可变长度的字段组成的更为复杂的协议时，
 * 你的ChannelHandler的实现将很快地变得难以维护。
 * 正如你所知的，你可以增加多个ChannelHandler到ChannelPipeline ,因此你可以把一整个ChannelHandler拆分成多个模块以减少应用的复杂程度，
 * 比如你可以把TimeClientHandler拆分成2个处理器：
 * ·TimeDecoder处理数据拆分的问题
 * ·TimeClientHandler原始版本的实现
 *  
 *  幸运地是，Netty提供了一个可扩展的类，帮你完成TimeDecoder的开发。
 *  
 * @author <a href="mailto:meacial@live.cn">Gangping Li</a>
 * 
 * @version 1.0 , Dec 21, 2015
 * 
 */
public class TimeServerHandler3 extends ChannelHandlerAdapter {

}

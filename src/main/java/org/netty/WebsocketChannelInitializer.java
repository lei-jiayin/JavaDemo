package org.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 通道初始化器
 * 用来加载通道处理器（channelHandle）
 * @author adx
 * @date 2020/7/8 15:55
 */
public class WebsocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // http handler 三连
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());

        //接收请求的路由,必须使用ws后缀结尾的url路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 消息处理器
        pipeline.addLast("handler", new WebSocketServerHandler());

    }
}

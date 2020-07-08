package org.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 启动服务器
 *
 * @author adx
 * @date 2020/7/8 15:46
 */
public class WebSocketNettyServer {

    public static void main(String[] args) {
        // 主线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程池
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            // 创建netty服务器启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebsocketChannelInitializer());
            Channel ch = bootstrap.bind(9090).sync().channel();
            //System.out.println("webSocket 启动端口为：" + 9090);
            //System.out.println("open your browser and navigate to http://localhost:" + 9090 + "/");
            ch.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

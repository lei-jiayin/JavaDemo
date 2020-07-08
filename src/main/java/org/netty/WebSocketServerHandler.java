package org.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author adx
 * @date 2020/7/8 15:59
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup groups = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 接收消息自动调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println("--------text值=" + text + "," + "当前类=WebSocketServerHandler.channelRead0()");
        for (Channel channel : groups){
            // 将消息发送到所有客户端
            channel.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date()) + "：" + text));
        }
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        groups.add(ctx.channel());
    }

}

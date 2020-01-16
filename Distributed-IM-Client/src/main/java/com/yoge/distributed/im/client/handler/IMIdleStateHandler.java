package com.yoge.distributed.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */
public class IMIdleStateHandler extends IdleStateHandler {

    public IMIdleStateHandler() {
        super(0, 10, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        ctx.channel().close();
    }
}

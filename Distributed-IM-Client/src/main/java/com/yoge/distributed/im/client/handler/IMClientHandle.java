package com.yoge.distributed.im.client.handler;

import com.yoge.distributed.im.common.protocol.CIMResponseProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */

@ChannelHandler.Sharable
@Slf4j
public class IMClientHandle extends SimpleChannelInboundHandler<CIMResponseProto.CIMResProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CIMResponseProto.CIMResProtocol msg) throws Exception {

    }
}

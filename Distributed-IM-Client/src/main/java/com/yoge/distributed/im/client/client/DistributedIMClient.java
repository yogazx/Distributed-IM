package com.yoge.distributed.im.client.client;

import com.yoge.distributed.im.client.handler.IMIdleStateHandler;
import com.yoge.distributed.im.client.service.RouteRequestService;
import com.yoge.distributed.im.client.vo.req.LoginReqVO;
import com.yoge.distributed.im.client.vo.res.DistributedIMServerResVO;
import com.yoge.distributed.im.common.protocol.CIMResponseProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */

@Component
public class DistributedIMClient {

    @Value("${im.user.userid}")
    private long userId;

    @Value("${im.user.username}")
    private String userName;

    @Autowired
    private RouteRequestService routeRequestService;

    private NioEventLoopGroup group = new NioEventLoopGroup(0, new DefaultThreadFactory("distributed-im-work"));

    @PostConstruct
    public void start() {
        // 登录 + 获取可用服务器ip:port
        DistributedIMServerResVO.ServerInfo serverInfo = userLogin();
        // 启动客户端
        startClient(serverInfo);
    }

    private void startClient(DistributedIMServerResVO.ServerInfo serverInfo) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        // 拆包解码
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(CIMResponseProto.CIMResProtocol.getDefaultInstance()));
                        // 拆包编码
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                    }
                });
    }

    /**
     * 登录+路由服务器
     *
     * @return 路由服务器信息
     */
    private DistributedIMServerResVO.ServerInfo userLogin() {
        LoginReqVO loginReqVO = new LoginReqVO(userId, userName);
        DistributedIMServerResVO.ServerInfo imServerInfo = null;

        return imServerInfo;
    }
}

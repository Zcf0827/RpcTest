package NettyTest;

import NettyTest.Serializer.KryoSerializer;
import NettyTest.Serializer.Serializer;
import NettyTest.coder.NettyKryoDecoder;
import NettyTest.coder.NettyKryoEncoder;
import NettyTest.entity.RpcRequest;
import NettyTest.entity.RpcResponse;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 1 定义传输对象实体
 * 2 获取传输的socket客户端以及 相应的输入输出流（netty封装）
 * 3 获取服务端（netty封装）
 * 4 序列化方式/编码起解码器
 * 5
 */

//发送消息 以及获取消息
public class NettyClient {
    private static  final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private final String host;
    private final int post;
    private static final Bootstrap b;

    public NettyClient(String host, int post){
        this.post = post;
        this.host = host;
    }
    //初始化netty客户端
    //客户端连接
    //网络通信（读写）
    //通道相关设置 超时连接 和心跳请求  通道处理读服务端消息
    //绑定编码解吗
    static {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        b = new Bootstrap();
        Serializer kryoSerializer = new KryoSerializer();
        b.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        socketChannel.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcResponse.class));
                        socketChannel.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class));
                        socketChannel.pipeline().addLast(new NettyClientHandler());


                    }
                });

    }
    //发送消息的方法

    //处理消息的方法


}

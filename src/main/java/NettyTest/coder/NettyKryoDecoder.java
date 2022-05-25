package NettyTest.coder;

import NettyTest.Serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class NettyKryoDecoder extends ByteToMessageDecoder {
    private final Serializer serializer;
    private final Class<?> genericClass;
    private  final static int DATA_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() > DATA_LENGTH){
            // 1判断合法性长度
            int datalength = byteBuf.readInt();
            if(datalength < 0){
                //log.err
                return;
            }
            //标记消息长度
            byteBuf.markReaderIndex();
            //判断可读字节数与消息长度
            if(byteBuf.readableBytes()  < datalength){ //消息不完整，重置
                byteBuf.resetReaderIndex();
            }
            // 转为bytes数组 并反序列化
             byte[] bytes  = new byte[datalength];
                byteBuf.readBytes(bytes);
                Object obj = serializer.deserialize(bytes, genericClass);
                list.add(obj);
        }
    }
}

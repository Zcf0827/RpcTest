package NettyTest.coder;

import NettyTest.Serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@AllArgsConstructor
@Slf4j
public class NettyKryoEncoder extends MessageToByteEncoder<Object> {
    private final Serializer serializer;
    private final Class<?> genericClass;
    /**
     *
     * @param channelHandlerContext
     * @param o
     * @param byteBuf
     * @throws Exception
     */

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if(genericClass.isInstance(o)){
            byte[] bytes = serializer.serialize(o);

            int dataLength = bytes.length;

            byteBuf.writeInt(dataLength);

            byteBuf.writeBytes(bytes);
        }
    }
}

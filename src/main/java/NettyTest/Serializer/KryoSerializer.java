package NettyTest.Serializer;

import NettyTest.common.SerializeException;
import NettyTest.entity.RpcRequest;
import NettyTest.entity.RpcResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.SerializedLambda;

public class KryoSerializer implements Serializer{

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() ->{
        Kryo kryo = new Kryo();
        kryo.register(RpcRequest.class);
        kryo.register(RpcResponse.class);
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    @Override
    public byte[] serialize(Object o) {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Output output = new Output(byteArrayOutputStream);
            Kryo kryo = kryoThreadLocal.get();
            kryo.writeObject(output, o);
            return output.toBytes();
        }catch (Exception e){
            throw new SerializeException("序列化失败");
        }finally {
            kryoThreadLocal.remove();
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try{
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Input intput = new Input(byteArrayInputStream);
            Kryo kryo = kryoThreadLocal.get();
            Object o = kryo.readObject(intput, clazz);
            return clazz.cast(o);//将一个Object变为该类型的对象T
        }catch (Exception e){
            throw new SerializeException("反序列化失败");
        }finally {
            kryoThreadLocal.remove();
        }
    }
}

package SocketRpc.common.Serailize;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianUtil {
    public static byte[] serialize(Object o) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output h2out = new Hessian2Output(bos);
        h2out.writeObject(o);
        h2out.flush();
        byte[] bytes = bos.toByteArray();
        bos.close();
        h2out.close();
        return  bytes;
    }
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Hessian2Input h2in = new Hessian2Input(bis);
        Object o = h2in.readObject();
        bis.close();
        h2in.close();
        return clazz.cast(o);
    }
}

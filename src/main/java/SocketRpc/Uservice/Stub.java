package SocketRpc.Uservice;

import SocketRpc.Entity.RpcRequest;
import SocketRpc.Entity.UserDTO;
import SocketRpc.common.Serailize.HessianUtil;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    public static UUserService getstub(Class clazz){
        InvocationHandler invocationHandler = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s = new Socket("127.0.0.1", 8888);
                InputStream inputStream = s.getInputStream();
                OutputStream outputStream = s.getOutputStream();

                //ByteArrayOutputStream bos = new ByteArrayOutputStream(outputStream);
                //写出方法相关信息
                //DataInputStream dis = new DataInputStream(inputStream);
                String methodname = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                RpcRequest request = new RpcRequest(clazz.getName(), methodname,parameterTypes,args);
                byte[] requestBytes = HessianUtil.serialize(request);
                outputStream.write(requestBytes);

                byte[] bytes = new byte[1024 * 4];
                inputStream.read(bytes);
                UserDTO user = HessianUtil.deserialize(bytes, UserDTO.class);
                return user;
            }
        };
        Object o = Proxy.newProxyInstance(UUserService.class.getClassLoader(), new Class[] {UUserService.class}, invocationHandler);
        return (UUserService) o;
    }
}

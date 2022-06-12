package SocketRpc;

import SocketRpc.Entity.RpcRequest;
import SocketRpc.Entity.UserDTO;
import SocketRpc.Iservice.IUserService;
import SocketRpc.Iservice.IUserServiceImpl;
import SocketRpc.common.Serailize.HessianUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //启动服务
    //监听客户端连接
    //获取客户端输出流
    //通过反射执行方法
    //使用输出流写回到客户端
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024 *4];
            inputStream.read(buffer);

            RpcRequest request =  HessianUtil.deserialize(buffer,RpcRequest.class);
            String interfaceName = request.getInterfaceName();
            Class[] parametertype = request.getParameterTypes();
            Object[] arg = request.getArgs();
            String methodName = request.getMethondname();

            //从注册中心找接口名字
            //Class clazz = null;

            Class clazz = Class.forName(interfaceName + "Impl");
            //clazz = IUserServiceImpl.class;
            Method method = clazz.getMethod(methodName, parametertype);
            UserDTO userDTO = (UserDTO) method.invoke(clazz.newInstance(), arg);
            //反序列化
            byte[] bytes = HessianUtil.serialize(userDTO);
            outputStream.write(bytes);
            socket.close();
        }
    }
}

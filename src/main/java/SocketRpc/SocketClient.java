package SocketRpc;

import SocketRpc.Entity.UserDTO;
import SocketRpc.Iservice.IUserService;
import SocketRpc.Uservice.Stub;
import SocketRpc.Uservice.UUserService;

public class SocketClient {
    public static void main(String[] args) {
        UUserService uUserService = Stub.getstub(IUserService.class);
        UserDTO userById = uUserService.findUserById("1");
        UserDTO userByName = uUserService.findUserByName("ccc");
        System.out.println(userByName.toString());
        System.out.println(userById.toString());

    }
}

package SocketRpc.Iservice;

import SocketRpc.Entity.UserDTO;

public interface IUserService {
    UserDTO findUserById(String id);

    UserDTO findUserByName(String name);
}

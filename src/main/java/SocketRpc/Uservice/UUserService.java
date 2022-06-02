package SocketRpc.Uservice;

import SocketRpc.Entity.UserDTO;

public interface UUserService {
    UserDTO findUserById(String id);
    UserDTO findUserByName(String name);
}

package SocketRpc.Iservice;

import SocketRpc.Entity.UserDTO;

public class IUserServiceImpl implements IUserService {
    @Override
    public UserDTO findUserById(String id) {
        UserDTO userDTO = new UserDTO(id,"aaa");
        return userDTO;
    }

    @Override
    public UserDTO findUserByName(String name) {
        return new UserDTO("2","ccc");
    }
}

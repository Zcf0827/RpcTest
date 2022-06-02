package SocketRpc.Entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UserDTO implements Serializable {
    private String id;
    private String name;
}

package SocketRpc.Entity;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methondname;
    private Class[] parameterTypes;
    private Object[] args;
}

package ZookeeperFind;

import java.net.InetSocketAddress;

public interface ServiceRegisitry {

    void RegisterService(String RpcServiceName, InetSocketAddress InetAdress);
}

package ZookeeperFind;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {

    InetSocketAddress findService(String RpcSreviceName) throws Exception;
}

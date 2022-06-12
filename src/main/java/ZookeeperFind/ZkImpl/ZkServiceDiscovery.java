package ZookeeperFind.ZkImpl;

import ZookeeperFind.CuratorUtils;
import ZookeeperFind.ServiceDiscovery;
import com.google.common.collect.Lists;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

public class ZkServiceDiscovery implements ServiceDiscovery {

    //TODO 负载均衡寻找adress

    @Override
    public InetSocketAddress findService(String RpcSreviceName) throws Exception {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> childrenNodes = CuratorUtils.getChildrenNodes(zkClient, RpcSreviceName);

        if(childrenNodes.isEmpty() || childrenNodes.size() == 0){
            throw new Exception("无法找到该远程服务");
        }

        String[] socketAdress = childrenNodes.get(0).split(":");

        return new InetSocketAddress(socketAdress[0], Integer.parseInt(socketAdress[1]));
    }
}

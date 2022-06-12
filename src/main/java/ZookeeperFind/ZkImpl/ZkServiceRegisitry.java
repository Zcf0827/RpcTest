package ZookeeperFind.ZkImpl;

import ZookeeperFind.CuratorUtils;
import ZookeeperFind.ServiceRegisitry;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

@Slf4j
public class ZkServiceRegisitry implements ServiceRegisitry {
    @Override
    public void RegisterService(String RpcServiceName, InetSocketAddress InetAdress) {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        String TotalName = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + RpcServiceName + InetAdress.toString();
        log.info("Service Register {}", TotalName);
        CuratorUtils.createNodePersitense(TotalName, zkClient);
    }
}

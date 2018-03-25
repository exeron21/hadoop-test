package win.bojack.bigdata.zookeepertest;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZKTest {

    @Test
    public void ls() throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper("slave1:2181,slave2:2181,slave3:2181", 5000, null);
        List<String> children = zk.getChildren("/", null);
        for (String s : children) {
            System.out.println(s);
        }
    }

    @Test
    public void lsAll() throws Exception {
        ls("/");
    }

    public void ls(String path) throws Exception {
        System.out.println(path);
        ZooKeeper zk = new ZooKeeper("slave1:2181,slave2:2181,slave3:2181", 5000, null);
        List<String> children = zk.getChildren(path, null);
        if (children == null || children.isEmpty()) {
            return;
        }
        for (String s : children) {
            if (path.equals("/")) {
                ls(path + s);
            } else {
                ls (path + "/" + s);
            }
        }
    }

    @Test
    public void write() throws Exception {
        ZooKeeper zk = new ZooKeeper("slave1:2181", 5000, null);
        zk.setData("/b","bbbb".getBytes(), 0);
    }

    /**
     * 创建临时节点
     * @throws Exception
     */
    @Test
    public void createNode () throws Exception {
        ZooKeeper zk = new ZooKeeper("slave1:2181", 5000, null);
        zk.create("/bbbb","bbb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("hello");
    }
}

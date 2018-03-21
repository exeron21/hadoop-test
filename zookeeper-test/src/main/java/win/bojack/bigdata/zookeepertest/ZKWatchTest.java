package win.bojack.bigdata.zookeepertest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class ZKWatchTest {


    /**
     * zookeeper的watcher机制循环调用测试。
     * zk.getData(path, watcher, stat) 调用这个方法时注册一个watcher，<br/>
     * 这个watch有一个回调函数，每次/b这个节点的数据被修改了，就会调用这个回调函数打印一句话，<br/>
     * 同时又注册了一个watcher，这个watcher里有个同样的回调函数，
     * 以实现循环调用 。
     * @throws Exception
     */
    @Test
    public void testWatch() throws Exception {
        final ZooKeeper zk = new ZooKeeper("slave1", 5000, null);
        final Stat stat = new Stat();
        Watcher w = new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    System.out.println("数据被修改了！！");
                    zk.getData("/b", this, stat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        byte[] data = zk.getData("/b", w, stat);

        System.out.println(new String(data));
        while(true) {
            Thread.sleep(10000);
        }
    }
}

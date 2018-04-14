package win.bojack.bigdata.kafka;

import java.util.Properties;

public class KafkaProperties {
    private Properties properties = new Properties();

    // 加载kafka配置
    public KafkaProperties() {
        Constant constant = new Constant();

        properties.put("zookeeper.connect", constant.ZK_CONNECT);
        properties.put("group.id", constant.GROUP_ID);
        properties.put("zookeeper.session.timeout.ms", "400");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset","smallest");
    }

    public Properties properties() {
        return properties;
    }
}

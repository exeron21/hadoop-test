package win.bojack.bigdata.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class TestProducer {
    private static Producer<Integer, String> initKafkaProducer(String brokerList) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);//格式：host1:port1,host2:port2,....
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 0);//a batch size of zero will disable batching entirely
        props.put(ProducerConfig.LINGER_MS_CONFIG, 0);//send message without delay
        props.put(ProducerConfig.ACKS_CONFIG, "1");//对应partition的leader写到本地后即返回成功。极端情况下，可能导致失败
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<Integer, String> kafkaProducer = new KafkaProducer<Integer, String>(props);
        return kafkaProducer;
    }

    private static Consumer<Integer, String> initKafkaConsumer(String zk) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
//        props.put(ConsumerConfig.)
        Consumer consumer = new KafkaConsumer(props);
        return consumer;
    }

    @Test
    public void testSend() throws ExecutionException, InterruptedException {
        List<String> lines = new ArrayList<String>();
        lines.add("hahahaaa");
        lines.add("0sdf09asdf");
        lines.add("aljdflkasd");
        Producer kafkaProducer = TestProducer.initKafkaProducer("slave1:9092");
        String topic = "test";
        for(String line : lines) {
            ProducerRecord<Integer, String> message = new ProducerRecord<Integer, String>(topic, line);
            kafkaProducer.send(message).get();
        }
    }
}

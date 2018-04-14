package win.bojack.bigdata.kafka;


public class ConsumerMain {
    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer();
        consumer.go();
    }
}

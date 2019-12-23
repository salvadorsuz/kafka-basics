package com.example.kafkaBasics.consumer;

import com.example.kafkaBasics.consumer.factory.ConsumerFactory;
import com.example.kafkaBasics.consumer.recordConsumer.SimpleRecordConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

@Service("IterativeDataSeekConsumer")
public class IterativeDataSeekConsumer{

    private static final Integer POLLING_MILLIS = 100;

    private static final Logger logger = LoggerFactory.getLogger(IterativeDataSeekConsumer.class);

    @Autowired
    @Qualifier("KafkaConsumerFactory")
    private ConsumerFactory kafkaConsumerFactory;

    @Autowired
    @Qualifier("SimpleRecordConsumer")
    private SimpleRecordConsumer simpleRecordConsumer;

    public Consumer generateConsumer(String topic, Integer partition, Long offsetToReadFrom) {
        // create consumer
        Consumer consumer = kafkaConsumerFactory.getConsumer();

        //subscribe topic
        consumer.subscribe(Arrays.asList(topic));

        //assign
        TopicPartition partitionToReadFrom = new TopicPartition(topic, partition);
        consumer.assign(Arrays.asList(partitionToReadFrom));

        //seek
        consumer.seek(partitionToReadFrom, offsetToReadFrom);

        return consumer;
    }

    public static void consumeRecords(Consumer consumer, Integer numberOfMessagesToRead, java.util.function.Consumer<ConsumerRecord> recordConsume) {
        // poll for new data
        logger.info("Consuming data************");

        int numberOfMessagesReadSoFar = 0;
        while(numberOfMessagesReadSoFar <= numberOfMessagesToRead){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(POLLING_MILLIS));

            for(ConsumerRecord<String, String> record:records) {
                numberOfMessagesReadSoFar += 1;
                recordConsume.accept(record);
            };
        }
    }

    public void consume(String topic, Integer partition, Long offsetToReadFrom, Integer numberOfMessagesToRead  ) {
        // create consumer
        final Consumer consumer = generateConsumer(topic, partition, offsetToReadFrom);
        logger.info("Consumer created************");

        //consume data from topic
        consumeRecords(consumer,numberOfMessagesToRead, simpleRecordConsumer);
        logger.info("Data consumed************");

        //close consumer
        consumer.close();
        logger.info("Consumer subscribed topic************");
    }
}

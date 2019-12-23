package com.example.kafkaBasics.consumer;

import com.example.kafkaBasics.consumer.factory.KafkaConsumerFactory;
import com.example.kafkaBasics.consumer.recordConsumer.SimpleRecordConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@Service("InfiniteDataConsumer")
public class InfiniteDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteDataConsumer.class);

    private static final Integer POLLING_MILLIS = 100;

    @Autowired
    @Qualifier("KafkaConsumerGroupedFactory")
    private KafkaConsumerFactory kafkaConsumerGroupedFactory;

    @Autowired
    private SimpleRecordConsumer simpleRecordConsumer;

    public void consume(String topic) {
        // create consumer
        Consumer consumer = kafkaConsumerGroupedFactory.getConsumer();
        logger.info("Consumer created************");

        //subscribe topic
        consumer.subscribe(Arrays.asList(topic));

        // poll for new data infinite
        logger.info("Consuming data************");
        Stream.generate(() -> consumer)
                .map(c -> c.poll(Duration.ofMillis(POLLING_MILLIS)))
                .forEach(records ->
                        records.forEach(simpleRecordConsumer)
                );
    }
}

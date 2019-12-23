package com.example.kafkaBasics.consumer;

import com.example.kafkaBasics.consumer.factory.KafkaConsumerFactory;
import com.example.kafkaBasics.consumer.recordConsumer.RecordConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

@Service("InfiniteDataConsumption")
public class InfiniteDataConsumption {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteDataConsumption.class);

    private static final Integer POLLING_MILLIS = 100;

    private Consumer<String, String> consumer;

    @Autowired
    public InfiniteDataConsumption(KafkaConsumerFactory consumerFactory) {
        this.consumer =   consumerFactory.getConsumer();
    }

    public void consume(String topic, CountDownLatch latch, RecordConsumer simpleRecordConsumer) {

        this.consumer.subscribe(Arrays.asList(topic));

        // poll for new data
        try {
            Stream.generate(() -> consumer)
                    .map(c -> c.poll(Duration.ofMillis(POLLING_MILLIS)))
                    .forEach(records ->
                            records.forEach(simpleRecordConsumer)
                    );
        } catch (WakeupException e) {
            logger.info("Received shutdown signal!");
        } finally {
            consumer.close();
            // tell our main code we're done with the consumer
            latch.countDown();
        }
    }

    public void shutdown() {
        // the wakeup() method is a special method to interrupt consumer.poll()
        // it will throw the exception WakeUpException
        consumer.wakeup();
    }

}

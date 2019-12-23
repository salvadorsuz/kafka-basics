package com.example.kafkaBasics.consumer;

import com.example.kafkaBasics.consumer.factory.ConsumerFactory;
import com.example.kafkaBasics.consumer.recordConsumer.RecordConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service("InfiniteDataThreadConsumer")
public class InfiniteDataThreadConsumer {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteDataThreadConsumer.class);

    private static final Integer LATCH = 1;

    @Autowired
    @Qualifier("SimpleRecordsConsumer")
    private RecordConsumer simpleRecordConsumer;

    @Autowired
    @Qualifier("KafkaConsumerFactory")
    private ConsumerFactory kafkaConsumerFactory;

    @Autowired
    private InfiniteDataConsumption dataConsumption;

    public void consume(String topic) {
        CountDownLatch latch = new CountDownLatch(LATCH);

        // start the thread
        Thread myThread = new Thread(() -> dataConsumption.consume(topic, latch, simpleRecordConsumer));
        myThread.start();

        // add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Caught shutdown hook");
            dataConsumption.shutdown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Application has exited");
        }
        ));

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Application got interrupted", e);
        } finally {
            logger.info("Application is closing");
        }

    }


}

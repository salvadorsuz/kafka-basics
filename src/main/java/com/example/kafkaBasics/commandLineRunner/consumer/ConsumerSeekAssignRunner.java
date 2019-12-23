package com.example.kafkaBasics.commandLineRunner.consumer;

import com.example.kafkaBasics.consumer.IterativeDataSeekConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class ConsumerSeekAssignRunner<K, V> implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerSeekAssignRunner.class);

    private static final Integer PARTITION = 0;

    private static final Long OFFSET_FROM = 15L;

    private static final Integer NUM_ITERATIONS = 5;

    @Value( "${kafka.broker.topic}" )
    private String topic;

    @Autowired
    @Qualifier("IterativeDataSeekConsumer")
    private IterativeDataSeekConsumer iterativeDataConsumer;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Producer SIMPLE runner************");
        iterativeDataConsumer.consume(topic, PARTITION, OFFSET_FROM, NUM_ITERATIONS);
    }
}

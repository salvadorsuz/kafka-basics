package com.example.kafkaBasics.commandLineRunner.consumer;

import com.example.kafkaBasics.consumer.InfiniteDataThreadConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsumerThreadRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerThreadRunner.class);

    @Value( "${kafka.broker.topic}" )
    private String topic;

    @Autowired
    @Qualifier("InfiniteDataThreadConsumer")
    private InfiniteDataThreadConsumer infiniteDataThreadConsumer;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Producer SIMPLE runner************");
        infiniteDataThreadConsumer.consume(topic);
    }
}

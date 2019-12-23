package com.example.kafkaBasics.commandLineRunner.producer;

import com.example.kafkaBasics.producer.IterativeDataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProducerSimpleRunner<K, V> implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProducerSimpleRunner.class);

    @Value( "${kafka.broker.topic}" )
    private String topic;

    private static final Integer NUM_ITERATIONS = 1000;

    @Autowired
    private IterativeDataProducer iterativeDataProducer;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Consumer SIMPLE runner************");
        iterativeDataProducer.produce(topic, NUM_ITERATIONS);
    }

}

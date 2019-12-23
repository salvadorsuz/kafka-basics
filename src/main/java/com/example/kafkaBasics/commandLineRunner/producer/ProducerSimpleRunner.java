package com.example.kafkaBasics.commandLineRunner.producer;

import com.example.kafkaBasics.producer.DataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProducerSimpleRunner<K, V> implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProducerSimpleRunner.class);

    @Value( "${kafka.broker.topic}" )
    private String topic;

    @Autowired
    @Qualifier("IterativeDataProducer")
    private DataProducer iterativeDataProducer;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Consumer SIMPLE runner************");
        iterativeDataProducer.produce(topic);
    }

}

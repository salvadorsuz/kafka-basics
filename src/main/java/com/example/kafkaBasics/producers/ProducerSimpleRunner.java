package com.example.kafkaBasics.producers;

import com.example.kafkaBasics.producers.builders.ProducerRecordBuilder;
import com.example.kafkaBasics.producers.factories.KafkaProducerFactory;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ProducerSimpleRunner<K, V> implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProducerSimpleRunner.class);

    private static final String TOPIC = "first_topic";
    private static final String MESSAGE = "Message :";

    @Autowired
    private KafkaProducerFactory producerFactory;

    private String getMessage(Integer n) {
        return new StringBuilder().append(MESSAGE).append(n).toString();
    }

    private ProducerRecord<String, String> getRecord(Integer n) {
        return new ProducerRecordBuilder().withTopic(TOPIC).withValue(getMessage(n)).build();
    }

    private List<Future<RecordMetadata>> sendData(Producer<String, String> producer) {
        // create a producer record and send data - asynchronous
        return IntStream.range(0, 100)
                .mapToObj(i -> getRecord(i))
                .map(msg -> producer.send(msg))
                .collect(Collectors.toList());
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Producer SIMPLE************");

        // create the producer
        final Producer<String, String> producer = producerFactory.getProducer();
        logger.debug("Producer created************");

        try {
            //send data
            List<Future<RecordMetadata>> data = sendData(producer);
            logger.debug("Data sent************");
        } finally {
            producer.flush();
            producer.close();
            logger.debug("Producer closed************");
        }
    }

}

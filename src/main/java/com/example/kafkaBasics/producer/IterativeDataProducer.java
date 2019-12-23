package com.example.kafkaBasics.producer;

import com.example.kafkaBasics.producer.builder.ProducerRecordBuilder;
import com.example.kafkaBasics.producer.factory.ProducerFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

@Service("IterativeDataProducer")
public class IterativeDataProducer {

    private static final Logger logger = LoggerFactory.getLogger(IterativeDataProducer.class);

    @Autowired
    @Qualifier("KafkaProducerFactory")
    private ProducerFactory kafkaProducerFactory;

    @Autowired
    @Qualifier("SimpleCallback")
    private Callback simpleCallback;

    private static final String MESSAGE = "Message :";

    private Function<Integer, String> getMessage =
            (Integer n) -> new StringBuilder().append(MESSAGE).append(n).toString();

    private BiFunction<String, String, ProducerRecord> getRecord =
            (String topic, String msg) -> new ProducerRecordBuilder().withTopic(topic).withValue(msg).build();

    public void produce(String topic, Integer numIterations) {
        // create producer
        Producer<String, String> producer = kafkaProducerFactory.getProducer();
        logger.info("Producer created************");

        try {
            //produce data to topic
            IntStream.range(0, numIterations).boxed()
                    .map(getMessage)
                    .map(msg -> getRecord.apply(topic, msg))
                    .forEach(record -> producer.send(record, simpleCallback));

            logger.info("Data sent************");
        } finally {
            producer.flush();
            producer.close();
            logger.debug("Producer closed************");
        }
    }
}

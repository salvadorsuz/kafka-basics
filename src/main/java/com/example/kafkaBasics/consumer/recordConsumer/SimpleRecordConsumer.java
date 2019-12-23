package com.example.kafkaBasics.consumer.recordConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SimpleRecordConsumer")
public class SimpleRecordConsumer implements RecordConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRecordConsumer.class);

    @Override
    public void accept(ConsumerRecord record) {
        logger.info("Key "+ record.key() + ", value: " + record.value());
        logger.info("Partition: "+ record.partition() + " Offset: " + record.offset());
    }
}

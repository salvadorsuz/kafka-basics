package com.example.kafkaBasics.producer.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SimpleCallback")
public class SimpleCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCallback.class);

    @Override
    public void onCompletion(RecordMetadata m, Exception e) {
        if ( e == null ) {
            logger.info("Received metadata \n" +
                    "Topic: " + m.topic() + "\n" +
                    "Partition: " + m.partition() + "\n" +
                    "Offset: "+ m.offset() + "\n");
        } else {
            logger.error("Error publishing event", e);
        }
    }
}

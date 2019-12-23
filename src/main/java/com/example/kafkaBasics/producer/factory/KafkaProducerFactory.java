package com.example.kafkaBasics.producer.factory;

import com.example.kafkaBasics.commons.delegate.ConfigPropertiesDelegate;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("KafkaProducerFactory")
public class KafkaProducerFactory implements ProducerFactory {

    @Autowired
    @Qualifier("KafkaProducerConfigPropertiesDelegate")
    private ConfigPropertiesDelegate configPropertiesDelegate;

    @Override
    public Producer<String, String> getProducer() {
        Properties properties = configPropertiesDelegate.getProperties();
        return new KafkaProducer<>(properties);
    }
}

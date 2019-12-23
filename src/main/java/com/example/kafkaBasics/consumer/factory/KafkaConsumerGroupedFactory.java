package com.example.kafkaBasics.consumer.factory;

import com.example.kafkaBasics.commons.delegate.ConfigPropertiesDelegate;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("KafkaConsumerGroupedFactory")
public class KafkaConsumerGroupedFactory implements ConsumerFactory {

    @Autowired
    @Qualifier("KafkaConsumerGroupedConfigPropertiesDelegate")
    private ConfigPropertiesDelegate kafkaConsumerGroupedConfigPropertiesDelegate;

    @Override
    public Consumer<String, String> getConsumer() {
        Properties properties = kafkaConsumerGroupedConfigPropertiesDelegate.getProperties();
        return new KafkaConsumer<>(properties);
    }
}

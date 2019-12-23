package com.example.kafkaBasics.producer.delegate;

import com.example.kafkaBasics.commons.delegate.KafkaConfigPropertiesDelegate;
import com.example.kafkaBasics.configuration.KafkaConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("KafkaProducerConfigPropertiesDelegate")
public class KafkaProducerConfigPropertiesDelegate extends KafkaConfigPropertiesDelegate {

    @Override
    public Properties getProperties(KafkaConfig config) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootStrapServer(config.getBroker()));
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}

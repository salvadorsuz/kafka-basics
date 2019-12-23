package com.example.kafkaBasics.consumer.deletegate;

import com.example.kafkaBasics.commons.delegate.KafkaConfigPropertiesDelegate;
import com.example.kafkaBasics.configuration.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("KafkaConsumerConfigPropertiesDelegate")
public class KafkaConsumerConfigPropertiesDelegate extends KafkaConfigPropertiesDelegate {

    @Override
    public Properties getProperties(KafkaConfig config) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootStrapServer(config.getBroker()));
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getConsumer().getOffsetReset() );

        return properties;
    }

}

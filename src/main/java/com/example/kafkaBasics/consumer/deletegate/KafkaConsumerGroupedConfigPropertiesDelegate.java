package com.example.kafkaBasics.consumer.deletegate;

import com.example.kafkaBasics.configuration.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("KafkaConsumerGroupedConfigPropertiesDelegate")
public class KafkaConsumerGroupedConfigPropertiesDelegate extends KafkaConsumerConfigPropertiesDelegate {

    @Override
    public Properties getProperties(KafkaConfig config) {
        Properties properties = super.getProperties();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, config.getConsumer().getGroupId());
        return properties;
    }

}

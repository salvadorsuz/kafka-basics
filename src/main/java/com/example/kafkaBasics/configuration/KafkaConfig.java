package com.example.kafkaBasics.configuration;

import com.example.kafkaBasics.configuration.beans.Broker;
import com.example.kafkaBasics.configuration.beans.Consumer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {
    private Broker broker;

    private Consumer consumer;

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}

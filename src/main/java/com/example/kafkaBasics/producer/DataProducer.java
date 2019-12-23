package com.example.kafkaBasics.producer;

@FunctionalInterface
public interface DataProducer {
    void produce(String topic);
}

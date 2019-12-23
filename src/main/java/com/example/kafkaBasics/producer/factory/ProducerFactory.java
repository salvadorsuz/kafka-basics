package com.example.kafkaBasics.producer.factory;

import org.apache.kafka.clients.producer.Producer;

@FunctionalInterface
public interface ProducerFactory<K, V> {
    Producer<K, V> getProducer();
}

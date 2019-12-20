package com.example.kafkaBasics.producers.factories;

import org.apache.kafka.clients.producer.Producer;

@FunctionalInterface
public interface ProducerFactory<K, V> {
    Producer<K, V> getProducer();
}

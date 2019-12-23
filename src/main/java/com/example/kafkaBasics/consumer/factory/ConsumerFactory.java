package com.example.kafkaBasics.consumer.factory;

import org.apache.kafka.clients.consumer.Consumer;

@FunctionalInterface
public interface ConsumerFactory<K, V> {

    Consumer<K, V> getConsumer();
}

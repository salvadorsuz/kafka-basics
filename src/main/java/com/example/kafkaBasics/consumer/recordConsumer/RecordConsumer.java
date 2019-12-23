package com.example.kafkaBasics.consumer.recordConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

public interface RecordConsumer extends Consumer<ConsumerRecord> {
}

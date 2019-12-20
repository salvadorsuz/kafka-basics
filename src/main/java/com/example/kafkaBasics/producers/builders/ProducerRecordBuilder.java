package com.example.kafkaBasics.producers.builders;

import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerRecordBuilder<K, V> {

    private String topic;

    private Integer partition = null;

    private K key = null;

    private V value;

    public ProducerRecordBuilder() {
    }

    public ProducerRecordBuilder withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public ProducerRecordBuilder withPartition(Integer partition) {
        this.partition = partition;
        return this;
    }

    public ProducerRecordBuilder withKey(K key) {
        this.key = key;
        return this;
    }

    public ProducerRecordBuilder withValue(V value) {
        this.value = value;
        return this;
    }

    public ProducerRecord<K, V> build() {
        return new ProducerRecord(topic, partition, key, value);
    }
}

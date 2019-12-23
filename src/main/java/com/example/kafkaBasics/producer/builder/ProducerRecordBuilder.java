package com.example.kafkaBasics.producer.builder;

import org.apache.kafka.clients.producer.ProducerRecord;

import static java.util.Objects.nonNull;

public class ProducerRecordBuilder<K, V> {

    private String topic;

    private Integer partition = null;

    private K key = null;

    private V value;

    public ProducerRecordBuilder() {
    }

    public ProducerRecordBuilder withTopic(String topic) {
        if(nonNull(topic)) {
            this.topic = topic;
        }
        return this;
    }

    public ProducerRecordBuilder withPartition(Integer partition) {
        if(nonNull(partition)) {
            this.partition = partition;
        }
        return this;
    }

    public ProducerRecordBuilder withKey(K key) {
        if(nonNull(key)) {
            this.key = key;
        }
        return this;
    }

    public ProducerRecordBuilder withValue(V value) {
        if(nonNull(value)) {
            this.value = value;
        }
        return this;
    }

    public ProducerRecord<K, V> build() {
        return new ProducerRecord(topic, partition, key, value);
    }
}

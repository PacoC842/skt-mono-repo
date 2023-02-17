package com.skytouch.model;


import lombok.Data;

@Data
public class KafkaMessage<V> {
    private V data;

    public KafkaMessage(V data) {
        this.data = data;
    }

    public KafkaMessage() {
    }

    public V getData() {
        return data;
    }
}

package com.skytouch.kafka;


import com.skytouch.model.KafkaMessage;

import java.util.UUID;

public interface KafkaMessageSender {
    void send(UUID requestId, KafkaMessage<String> message);
}

package com.skytouch.event;

import com.skytouch.model.KafkaMessage;
import com.skytouch.senderreceiver.SenderReceiver;
import com.skytouch.senderreceiver.SenderReceiverMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class KafkaEvent {

    private final SenderReceiverMap<UUID, String> senderReceiverMap;

    public KafkaEvent(SenderReceiverMap<UUID, String> senderReceiverMap) {
        this.senderReceiverMap = senderReceiverMap;
    }

    @KafkaListener(topics = "topic-for-demo", groupId = "demo")
    public void listenGroupFoo(ConsumerRecord<String, KafkaMessage<String>> record) {
        UUID rqId = this.getRqId(record.headers());
        if (senderReceiverMap.containsKey(rqId)) {
            SenderReceiver<String> stringSenderReceiver = senderReceiverMap.get(rqId);
            stringSenderReceiver.send(record.value().getData());
        }
    }
    private UUID getRqId(Headers headers) {
        Header headerRqId = headers.headers("RQ_ID").iterator().next();
        if (Objects.isNull(headerRqId)) return null;
        String rqIdStr = new String(headerRqId.value());
        return UUID.fromString(rqIdStr);
    }
}

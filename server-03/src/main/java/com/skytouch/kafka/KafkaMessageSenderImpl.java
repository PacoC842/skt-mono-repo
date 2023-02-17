package com.skytouch.kafka;

import com.skytouch.model.KafkaMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
public class KafkaMessageSenderImpl implements KafkaMessageSender {

    private static final String RQ_ID = "RQ_ID";
    private final KafkaTemplate<String, KafkaMessage<String>> kafkaTemplate;
    @Value("${kafka.topic.topic-for-demo}")
    private String topic;

    public KafkaMessageSenderImpl(KafkaTemplate<String, KafkaMessage<String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(final UUID requestId, final KafkaMessage<String> message) {

        ProducerRecord<String, KafkaMessage<String>> record = new ProducerRecord<>(topic, message);

        record.headers().add(new RecordHeader(RQ_ID, requestId.toString().getBytes()));
        ListenableFuture<SendResult<String, KafkaMessage<String>>> future = kafkaTemplate.send(record);
        future.addCallback((success) -> {
        }, System.out::println);
        kafkaTemplate.flush();
    }

}

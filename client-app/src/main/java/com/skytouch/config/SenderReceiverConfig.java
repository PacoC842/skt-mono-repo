package com.skytouch.config;

import com.skytouch.senderreceiver.SenderReceiverMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SenderReceiverConfig {
    @Bean
    @Scope("singleton")
    public SenderReceiverMap<UUID, String> senderReceiverMap() {
        return new SenderReceiverMap<UUID, String>(new ConcurrentHashMap<>());
    }
}

package com.skytouch.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.model.Booklist;
import com.skytouch.model.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class BookService {

    private final String requestTopic = "request-topic";
    private final String requestReplyTopic = "requestreply-topic";
    @Value("${endpointServer}")
    private String endpointServer;

    public Booklist getBooksRest() {
        UUID requestId = UUID.randomUUID();
//        while (senderReceiverMap.containsKey(requestId)) {
//            requestId = UUID.randomUUID();
//        }
        return this.GetAllFromServer(requestId, "post Call");
    }

    private Booklist GetAllFromServer(final UUID requestId, final String text) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(text + "pl2j407407");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJsonStr = null;

        try {
            requestJsonStr = new ObjectMapper().writeValueAsString(new RequestDto(requestId, text));
            System.out.println(requestJsonStr + "requestJsonStr pl2j407");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<>(requestJsonStr, headers);
        return restTemplate.postForObject(endpointServer, request, Booklist.class);
    }
}

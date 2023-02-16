package com.skytouch.model;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestDto {
    private UUID requestId;
    private String data;

    public RequestDto(UUID requestId, String data) {
        this.requestId = requestId;
        this.data = data;
    }

    public RequestDto() {
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getData() {
        return data;
    }
}

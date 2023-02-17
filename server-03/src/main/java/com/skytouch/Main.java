package com.skytouch;


import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        String superSecretId = "f000aa01-0451-4000-b000-000000000000";
        String superSecretId = "e7fbca90-2e7a-436b-a2d4-205343a2caf6";

        UUID id = UUID.fromString(superSecretId);
        System.out.println(id);
    }
}
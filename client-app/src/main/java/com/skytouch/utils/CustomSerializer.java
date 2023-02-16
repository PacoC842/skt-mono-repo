//package com.skytouch.utils;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.skytouch.model.Booklist;
//
//
//public class CustomSerializer implements Serializer<Booklist> {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public byte[] serialize(String s, Booklist booklist) {
//        try {
//            if (booklist == null) {
//                System.out.println("Null received at serializing");
//                return null;
//            }
//            System.out.println("Serializing...");
//            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//            return objectMapper.writeValueAsBytes(booklist);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}

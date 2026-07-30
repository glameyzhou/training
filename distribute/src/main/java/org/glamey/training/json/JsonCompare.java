package org.glamey.training.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCompare {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static boolean equals(String json1, String json2) {
        try {
            return MAPPER.readTree(json1).equals(MAPPER.readTree(json2));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String json1 = "{\"name\":\"glamey\",\"age\":18}";
        String json2 = "{\"age\":18,\"name\":\"glamey\"}";
        System.out.println(equals(json1, json2));
    }
}

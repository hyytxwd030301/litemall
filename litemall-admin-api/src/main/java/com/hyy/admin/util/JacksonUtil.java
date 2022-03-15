package com.hyy.admin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class JacksonUtil {

    public static String get(String body,String field){
        JsonMapper jsonMapper=new JsonMapper();
        JsonNode jsonNode=null;
        try {
            jsonNode = jsonMapper.readTree(body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode value = jsonNode.get(field);
        if (value!=null){
            return value.asText();
        }
        return null;

    }
}

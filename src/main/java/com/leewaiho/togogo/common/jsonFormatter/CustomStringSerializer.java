package com.leewaiho.togogo.common.jsonFormatter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/8
 * Project togogo-shixun
 */
public class CustomStringSerializer extends JsonSerializer<String> {
    
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(JSONObject.parse(s));
    }
}

package com.leewaiho.togogo.common.jsonFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.leewaiho.togogo.module.sys.model.user.TSUser;

import java.io.IOException;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
public class UsernameSerializer extends JsonSerializer<TSUser> {
    @Override
    public void serialize(TSUser user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(user.getUsername());
    }
}

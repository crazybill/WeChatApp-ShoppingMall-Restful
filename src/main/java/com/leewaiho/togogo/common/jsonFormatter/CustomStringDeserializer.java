package com.leewaiho.togogo.common.jsonFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
public class CustomStringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        return treeNode.toString();
    }
}

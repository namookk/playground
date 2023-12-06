package com.playground.user.payload.support;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CustomDateStringDeserializer extends StdDeserializer<String> {

    public CustomDateStringDeserializer() {
        this(null);
    }

    public CustomDateStringDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JacksonException {
        String date = jsonParser.getText();
        return date.replaceAll("-", "");
    }
}

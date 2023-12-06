package com.playground.user.payload.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class HyphenFormatSerializer extends StdSerializer<String> {

    public HyphenFormatSerializer() {
        this(null);
    }

    public HyphenFormatSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        final String sep = "-";
        if (value != null && value.length() == 8) {
            value = value.substring(0, 4) + sep + value.substring(4, 6) + sep + value.substring(6);
        }
        gen.writeString(value);
    }
}

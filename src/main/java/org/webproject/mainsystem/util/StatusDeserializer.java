package org.webproject.mainsystem.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/*public class StatusDeserializer extends JsonDeserializer<Status> {

    @Override
    public Status deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int ordinal = p.getIntValue();
        Status[] values = Status.values();
        if (ordinal < 0 || ordinal >= values.length) {
            throw new IllegalArgumentException("Invalid ordinal value: " + ordinal);
        }
        return values[ordinal];
    }
}*/

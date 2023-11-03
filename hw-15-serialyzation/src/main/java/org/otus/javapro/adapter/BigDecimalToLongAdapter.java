package org.otus.javapro.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalToLongAdapter implements JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {
    @Override
    public JsonElement serialize(BigDecimal bigDecimal, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(bigDecimal.longValue());
    }

    @Override
    public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            long value = json.getAsLong();
            return BigDecimal.valueOf(value);
        } catch (NumberFormatException e) {
            throw new JsonParseException("Invalid number: " + json.getAsString(), e);
        }
    }
}

package org.otus.javapro.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;

public class BooleanToInt implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
    @Override
    public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
        var result = src ? 1 : 0;
        return new JsonPrimitive(result);
    }

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsInt() == 1;
    }
}

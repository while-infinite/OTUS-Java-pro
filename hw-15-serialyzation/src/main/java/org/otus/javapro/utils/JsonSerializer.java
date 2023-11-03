package org.otus.javapro.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.otus.javapro.adapter.BigDecimalToLongAdapter;
import org.otus.javapro.adapter.BooleanToInt;
import org.otus.javapro.adapter.DateTimeToString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JsonSerializer {

    private JsonSerializer() {
    }

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(BigDecimal.class, new BigDecimalToLongAdapter())
            .registerTypeAdapter(LocalDateTime.class, new DateTimeToString())
            .registerTypeAdapter(Boolean.class, new BooleanToInt())
            .create();

    public static <T> T readJson(Class<T> type, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void writeJson(T object, Class<T> type, String path) throws IOException {
        try (JsonWriter writer = new JsonWriter(new FileWriter(path))) {
            gson.toJson(object, type, writer);
        }
    }
}

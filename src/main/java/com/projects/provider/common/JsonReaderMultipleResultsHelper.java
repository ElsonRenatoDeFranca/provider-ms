package com.projects.provider.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class JsonReaderMultipleResultsHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonReaderMultipleResultsHelper() {
    }

    public static <T> List<T> mockMultipleResultsFromJson(String fullPathMockResultFilename, Class<T> typeResultClass) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fullPathMockResultFilename);
        return new ObjectMapper().readValue(is, new TypeReference<List<T>>() {});

    }
}

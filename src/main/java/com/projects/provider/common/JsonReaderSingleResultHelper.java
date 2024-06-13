package com.projects.provider.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class JsonReaderSingleResultHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonReaderSingleResultHelper() {
    }

    public static <T> T mockSingleResultFromJson(String fullPathMockResultFilename, Class<T> typeResultClass) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fullPathMockResultFilename);
        return OBJECT_MAPPER.readValue(is, typeResultClass);
    }
}

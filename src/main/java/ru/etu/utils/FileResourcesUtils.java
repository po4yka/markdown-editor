package ru.etu.utils;

import java.io.InputStream;

public class FileResourcesUtils {

    public InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}

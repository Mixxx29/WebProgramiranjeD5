package rs.raf.webprogramiranjed5.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import rs.raf.webprogramiranjed5.model.Post;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FileManager {
    INSTANCE;

    public static File getFile(String path) {
        URL resource = INSTANCE.getClass().getClassLoader().getResource(path);
        if (resource == null) return null;
        return new File(resource.getFile());
    }

    public static <T> List<T> toList(Class<T[]> clazz, String path) {
        File file = getFile(path);
        if (file == null) return null;

        // Create object mapper
        ObjectMapper mapper = new ObjectMapper();

        List<T> posts;
        try {
            posts = Arrays.asList(mapper.readValue(file, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new ArrayList<>(posts);
    }

    public static <T> File toFile(List<T> list, String path) {
        File file = FileManager.getFile(path);
        if (file == null) return null;

        // Write posts back to file
        ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }
}

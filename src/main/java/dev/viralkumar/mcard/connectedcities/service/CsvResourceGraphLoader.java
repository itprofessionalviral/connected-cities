package dev.viralkumar.mcard.connectedcities.service;

import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class CsvResourceGraphLoader {

    public void loadRoutes(Resource csvResource, BiConsumer<String, String> routeConsumer) {
        try {
            // Opencsv library can be used.
            try (Stream<String> lines = getLines(csvResource)) {
                lines.map(line -> line.split(","))
                        .filter(values -> values.length == 2)
                        .forEach(values -> {
                            final String source = values[0].trim();
                            final String destination = values[1].trim();
                            routeConsumer.accept(source, destination);
                        });
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse file", e);
        }
    }

    private Stream<String> getLines(Resource csvResource) throws IOException {
        Stream<String> lines;
        if (csvResource.isFile()) {
            lines = Files.lines(csvResource.getFile().toPath());
        } else {
            lines = new BufferedReader(new InputStreamReader(csvResource.getInputStream(),
                    StandardCharsets.UTF_8)).lines();
        }
        return lines;
    }
}

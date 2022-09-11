package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName)  {
        Path filePath = createPathFromFileName(fileName);

        try (Stream<String> fileLinesStream = raedFile(filePath)) {
            return fileLinesStream.collect(joining("\n"));
        }
    }

    private static Stream<String> raedFile(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createPathFromFileName(String fileName) {
        URL fileURL= FileReaders.class.getClassLoader().getResource(fileName);
        try {
            return Paths.get(Objects.requireNonNull(fileURL).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

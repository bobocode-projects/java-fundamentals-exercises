package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

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
    public static String readWholeFile(String fileName) {
        try  {
            var fileUrl = FileReaders.class.getClassLoader().getResource(fileName);
            var filePath = Paths.get(fileUrl.toURI());
            return Files.readString(filePath);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}

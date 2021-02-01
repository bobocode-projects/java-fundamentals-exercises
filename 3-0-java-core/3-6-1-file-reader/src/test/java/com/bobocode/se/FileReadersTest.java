package com.bobocode.se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReadersTest {

    @Test
    void testReadWholeFileOnEmptyFile() {
        String fileContent = FileReaders.readWholeFile("empty.txt");

        assertEquals("", fileContent);

    }

    @Test
    void testReadWholeFileOnFileWithEmptyLines() {
        String fileContent = FileReaders.readWholeFile("lines.txt");

        assertEquals("Hey!\n" +
                "\n" +
                "What's up?\n" +
                "\n" +
                "Hi!", fileContent);
    }

    @Test
    void testReadWholeFile() {
        String fileContent = FileReaders.readWholeFile("simple.txt");

        assertEquals("Hello!\n" + "It's a test file.", fileContent);
    }
}

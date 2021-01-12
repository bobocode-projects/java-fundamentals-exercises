package com.bobocode.file_stats;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileStatsTest {

    @Test
    void testCreateFileStatsFromExistingFile() {
        FileStats fileStats = FileStats.from("sotl.txt");
    }

    @Test
    void testCreateFileStatsFromNonExistingFile() {
        Assertions.assertThrows(FileStatsException.class, () -> FileStats.from("blahblah.txt"));
    }

    @Test
    void testGetCharCount() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        int aCharCountInLambdaArticle = lambdaArticleFileStats.getCharCount('a');
        int bCharCountInSpringArticle = springCloudArticleFileStats.getCharCount('b');

        assertEquals(2345, aCharCountInLambdaArticle);
        assertEquals(4, bCharCountInSpringArticle);
    }

    @Test
    void testGetMostPopularCharacter() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        char mostPopularCharacterInLambdaArticle = lambdaArticleFileStats.getMostPopularCharacter();
        char mostPopularCharacterInSpringArticle = springCloudArticleFileStats.getMostPopularCharacter();

        System.out.println(mostPopularCharacterInSpringArticle);

        assertEquals('e', mostPopularCharacterInLambdaArticle);
        assertEquals('e', mostPopularCharacterInSpringArticle);
    }

    @Test
    void testContainsCharacter() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        boolean lambdaArticleContainsExistingCharacter = lambdaArticleFileStats.containsCharacter('a');
        boolean lambdaArticleContainsWhitespace = lambdaArticleFileStats.containsCharacter(' ');
        boolean springArticleContainsExistingCharacter = springCloudArticleFileStats.containsCharacter('b');
        boolean springArticleContainsWhitespace = springCloudArticleFileStats.containsCharacter(' ');

        assertTrue(lambdaArticleContainsExistingCharacter);
        assertFalse(lambdaArticleContainsWhitespace);
        assertTrue(springArticleContainsExistingCharacter);
        assertFalse(springArticleContainsWhitespace);
    }
}

package com.bobocode.se;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileStatsTest {

    @Test
    @Order(1)
    void createFileStatsFromExistingFile() {
        FileStats fileStats = FileStats.from("sotl.txt");
    }

    @Test
    @Order(2)
    void createFileStatsFromNonExistingFile() {
        assertThatThrownBy(() -> FileStats.from("blahblah.txt")).isInstanceOf(FileStatsException.class);
    }

    @Test
    @Order(3)
    void getCharCount() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        int aCharCountInLambdaArticle = lambdaArticleFileStats.getCharCount('a');
        int bCharCountInSpringArticle = springCloudArticleFileStats.getCharCount('b');

        assertThat(aCharCountInLambdaArticle).isEqualTo(2345);
        assertThat(bCharCountInSpringArticle).isEqualTo(4);
    }

    @Test
    @Order(4)
    void getMostPopularCharacter() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        char mostPopularCharacterInLambdaArticle = lambdaArticleFileStats.getMostPopularCharacter();
        char mostPopularCharacterInSpringArticle = springCloudArticleFileStats.getMostPopularCharacter();

        System.out.println(mostPopularCharacterInSpringArticle);

        assertThat(mostPopularCharacterInLambdaArticle).isEqualTo('e');
        assertThat(mostPopularCharacterInSpringArticle).isEqualTo('e');
    }

    @Test
    @Order(5)
    void containsCharacter() {
        FileStats lambdaArticleFileStats = FileStats.from("sotl.txt");
        FileStats springCloudArticleFileStats = FileStats.from("scosb.txt");

        boolean lambdaArticleContainsExistingCharacter = lambdaArticleFileStats.containsCharacter('a');
        boolean lambdaArticleContainsWhitespace = lambdaArticleFileStats.containsCharacter(' ');
        boolean springArticleContainsExistingCharacter = springCloudArticleFileStats.containsCharacter('b');
        boolean springArticleContainsWhitespace = springCloudArticleFileStats.containsCharacter(' ');

        assertThat(lambdaArticleContainsExistingCharacter).isTrue();
        assertThat(lambdaArticleContainsWhitespace).isFalse();
        assertThat(springArticleContainsExistingCharacter).isTrue();
        assertThat(springArticleContainsWhitespace).isFalse();
    }
}
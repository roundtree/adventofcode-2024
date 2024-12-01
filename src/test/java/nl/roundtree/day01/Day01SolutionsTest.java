package nl.roundtree.day01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day01SolutionsTest {

    @Test
    void setGetSumOfLocationDistances() {
        final Locations locations = PuzzleInputReader.readPuzzleInput("day01/day01_test_puzzleinput.txt");

        assertThat(locations.sumOfAllLocationDistances()).isEqualTo(11);
    }

    @Test
    void testDay01PuzzleSolutionToPart1() {
        final Locations locations = PuzzleInputReader.readPuzzleInput("day01/day01_puzzleinput.txt");

        assertThat(locations.sumOfAllLocationDistances()).isEqualTo(2196996);
    }

    @Test
    void testCalculateSimilarityScore() {
        final Locations locations = PuzzleInputReader.readPuzzleInput("day01/day01_test_puzzleinput.txt");

        assertThat(locations.calculateSimilarityScore()).isEqualTo(31);
    }

    @Test
    void testDay01PuzzleSolutionToPart2() {
        final Locations locations = PuzzleInputReader.readPuzzleInput("day01/day01_puzzleinput.txt");

        assertThat(locations.calculateSimilarityScore()).isEqualTo(23655822);
    }

}
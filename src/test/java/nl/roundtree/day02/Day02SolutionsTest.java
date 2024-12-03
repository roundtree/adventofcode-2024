package nl.roundtree.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02SolutionsTest {

    @Test
    void setGetAmountOfSafeReports() {
        final Reports locations = PuzzleInputReader.readPuzzleInput("day02/day02_test_puzzleinput.txt");

        assertThat(locations.getAmountOfSafeReports()).isEqualTo(2);
    }

    @Test
    void testDay02PuzzleSolutionToPart1() {
        final Reports locations = PuzzleInputReader.readPuzzleInput("day02/day02_puzzleinput.txt");

        assertThat(locations.getAmountOfSafeReports()).isEqualTo(334);
    }

    @Test
    void setGetAmountOfSafeReportsWithDampening() {
        final Reports locations = PuzzleInputReader.readPuzzleInput("day02/day02_test_puzzleinput.txt");

        assertThat(locations.getAmountOfSafeReportsWithDampening()).isEqualTo(4);
    }

    @Test
    void testDay02PuzzleSolutionToPart2() {
        final Reports locations = PuzzleInputReader.readPuzzleInput("day02/day02_puzzleinput.txt");

        assertThat(locations.getAmountOfSafeReportsWithDampening()).isEqualTo(400);
    }
}
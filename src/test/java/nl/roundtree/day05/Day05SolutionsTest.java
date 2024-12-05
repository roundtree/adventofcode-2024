package nl.roundtree.day05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day05SolutionsTest {

    @Test
    void testSumOfMiddlePageNumbersOfValidUpdates() {
        final PrintRequest printRequest = PuzzleInputReader.readPuzzleInput("day05/day05_test_puzzleinput.txt");
        
        assertThat(printRequest.sumOfMiddlePageNumbersOfValidUpdates()).isEqualTo(143);
    }

    @Test
    void testDay05PuzzleSolutionToPart1() {
        final PrintRequest printRequest = PuzzleInputReader.readPuzzleInput("day05/day05_puzzleinput.txt");

        assertThat(printRequest.sumOfMiddlePageNumbersOfValidUpdates()).isEqualTo(4905);
    }

    @Test
    void testSumOfMiddlePageNumbersOfCorrectedUpdates() {
        final PrintRequest printRequest = PuzzleInputReader.readPuzzleInput("day05/day05_test_puzzleinput.txt");

        assertThat(printRequest.sumOfMiddlePageNumbersOfCorrectedUpdates()).isEqualTo(123);
    }

    @Test
    void testDay05PuzzleSolutionToPart2() {
        final PrintRequest printRequest = PuzzleInputReader.readPuzzleInput("day05/day05_puzzleinput.txt");

        assertThat(printRequest.sumOfMiddlePageNumbersOfCorrectedUpdates()).isEqualTo(6204);
    }
}
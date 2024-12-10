package nl.roundtree.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day10SolutionsTest {

    @Test
    void testSumOfTrailheadScores() {
        final TopographicMap map = PuzzleInputReader.readPuzzleInput("day10/day10_test_puzzleinput.txt");

        assertThat(map.sumOfTrailheadScores()).isEqualTo(36);
    }

    @Test
    void testDay10PuzzleSolutionToPart1() {
        final TopographicMap map = PuzzleInputReader.readPuzzleInput("day10/day10_puzzleinput.txt");

        assertThat(map.sumOfTrailheadScores()).isEqualTo(468);
    }

    @Test
    void testSumOfTrailheadRatings() {
        final TopographicMap map = PuzzleInputReader.readPuzzleInput("day10/day10_test_puzzleinput.txt");

        assertThat(map.sumOfTrailheadRatings()).isEqualTo(81);
    }

    @Test
    void testDay10PuzzleSolutionToPart2() {
        final TopographicMap map = PuzzleInputReader.readPuzzleInput("day10/day10_puzzleinput.txt");

        assertThat(map.sumOfTrailheadRatings()).isEqualTo(966);
    }
}
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
//
//    @Test
//    void testCalculateChecksumMovingFileBlocks() {
//        final DiskMap map = PuzzleInputReader.readPuzzleInput("day10/day10_test_puzzleinput.txt");
//
//        assertThat(map.calculateChecksumMovingFileBlocks()).isEqualTo(2858);
//    }
//
//    @Test
//    void testDay10PuzzleSolutionToPart2() {
//        final DiskMap map = PuzzleInputReader.readPuzzleInput("day10/day10_puzzleinput.txt");
//
//        assertThat(map.calculateChecksumMovingFileBlocks()).isEqualTo(6398096697992L);
//    }
}
package nl.roundtree.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day09SolutionsTest {

    @Test
    void testCalculateChecksumMovingFiles() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_test_puzzleinput.txt");
        
        assertThat(map.calculateChecksumMovingFiles()).isEqualTo(1928L);
    }

    @Test
    void testDay09PuzzleSolutionToPart1() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_puzzleinput.txt");

        assertThat(map.calculateChecksumMovingFiles()).isEqualTo(6370402949053L);
    }

    @Test
    void testCalculateChecksumMovingFileBlocks() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_test_puzzleinput.txt");

        assertThat(map.calculateChecksumMovingFileBlocks()).isEqualTo(2858);
    }

    @Test
    void testDay09PuzzleSolutionToPart2() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_puzzleinput.txt");

        assertThat(map.calculateChecksumMovingFileBlocks()).isEqualTo(6398096697992L);
    }
}
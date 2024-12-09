package nl.roundtree.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day09SolutionsTest {

    @Test
    void testCalculateChecksum() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_test_puzzleinput.txt");
        
        assertThat(map.calculateChecksum()).isEqualTo(1928L);
    }

    @Test
    void testDay09PuzzleSolutionToPart1() {
        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_puzzleinput.txt");

        assertThat(map.calculateChecksum()).isEqualTo(6370402949053L);
    }

//    @Test
//    void testCountRepeatingAntinodes() {
//        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_test_puzzleinput.txt");
//
////        assertThat(map.countRepeatingAntinodes()).isEqualTo(34);
//    }
//
//    @Test
//    void testDay09PuzzleSolutionToPart2() {
//        final DiskMap map = PuzzleInputReader.readPuzzleInput("day09/day09_puzzleinput.txt");
//
////        assertThat(map.countRepeatingAntinodes()).isEqualTo(1115);
//    }
}
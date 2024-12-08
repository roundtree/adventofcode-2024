package nl.roundtree.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day08SolutionsTest {

    @Test
    void testCountAntinodes() {
        final Map map = PuzzleInputReader.readPuzzleInput("day08/day08_test_puzzleinput.txt");
        
        assertThat(map.countAntinodes()).isEqualTo(14);
    }

    @Test
    void testDay08PuzzleSolutionToPart1() {
        final Map map = PuzzleInputReader.readPuzzleInput("day08/day08_puzzleinput.txt");

        assertThat(map.countAntinodes()).isEqualTo(311);
    }


    @Test
    void testCountRepeatingAntinodes() {
        final Map map = PuzzleInputReader.readPuzzleInput("day08/day08_test_puzzleinput.txt");

        assertThat(map.countRepeatingAntinodes()).isEqualTo(34);
    }

    @Test
    void testDay08PuzzleSolutionToPart2() {
        final Map map = PuzzleInputReader.readPuzzleInput("day08/day08_puzzleinput.txt");

        assertThat(map.countRepeatingAntinodes()).isEqualTo(1115);
    }
}
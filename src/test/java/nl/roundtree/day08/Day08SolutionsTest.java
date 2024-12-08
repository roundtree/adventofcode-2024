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
//    
//
//    @Test
//    void testSumOfValidCalculationsWithConcatenation() {
//        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day08/day08_test_puzzleinput.txt");
//
//        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY, CONCAT)).isEqualTo(11387L);
//    }
//
//    @Test
//    void testDay08PuzzleSolutionToPart2() {
//        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day08/day08_puzzleinput.txt");
//
//        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY, CONCAT)).isEqualTo(401477450831495L);
//    }
}
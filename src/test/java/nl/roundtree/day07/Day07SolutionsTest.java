package nl.roundtree.day07;

import org.junit.jupiter.api.Test;

import static nl.roundtree.day07.Calibration.Operator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day07SolutionsTest {

    @Test
    void testSumOfValidCalculations() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_test_puzzleinput.txt");
        
        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY)).isEqualTo(3749L);
    }

    @Test
    void testDay07PuzzleSolutionToPart1() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_puzzleinput.txt");

        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY)).isEqualTo(2314935962622L);
    }
    

    @Test
    void testSumOfValidCalculationsWithConcatenation() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_test_puzzleinput.txt");

        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY, CONCAT)).isEqualTo(11387L);
    }

    @Test
    void testDay07PuzzleSolutionToPart2() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_puzzleinput.txt");

        assertThat(calibration.sumOfValidCalculations(ADD, MULTIPLY, CONCAT)).isEqualTo(401477450831495L);
    }
}
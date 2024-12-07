package nl.roundtree.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day07SolutionsTest {

    @Test
    void testSumOfValidCalculations() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_test_puzzleinput.txt");
        
        assertThat(calibration.sumOfValidCalculations()).isEqualTo(3749L);
    }

    @Test
    void testDay07PuzzleSolutionToPart1() {
        final Calibration calibration = PuzzleInputReader.readPuzzleInput("day07/day07_puzzleinput.txt");

        assertThat(calibration.sumOfValidCalculations()).isEqualTo(2314935962622L);
    }
    
//
//    @Test
//    void testAmountOfObstructionPlacementsCausingALoop() {
//        final Room room = PuzzleInputReader.readPuzzleInput("day07/day07_test_puzzleinput.txt");
//        
//        assertThat(room.getAmountOfObstructionPlacementsCausingALoop()).isEqualTo(6);
//    }
//
//    @Test
//    void testDay07PuzzleSolutionToPart2() {
//        final Room room = PuzzleInputReader.readPuzzleInput("day07/day07_puzzleinput.txt");
//
//        assertThat(room.getAmountOfObstructionPlacementsCausingALoop()).isEqualTo(1928);
//    }
}
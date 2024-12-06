package nl.roundtree.day06;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day06SolutionsTest {

    @Test
    void testSumOfMiddlePageNumbersOfValidUpdates() {
        final Room room = PuzzleInputReader.readPuzzleInput("day06/day06_test_puzzleinput.txt");
        
        assertThat(room.getNumberOfStepsBeforeLeavingRoom()).isEqualTo(41);
    }

    @Test
    void testDay06PuzzleSolutionToPart1() {
        final Room room = PuzzleInputReader.readPuzzleInput("day06/day06_puzzleinput.txt");

        assertThat(room.getNumberOfStepsBeforeLeavingRoom()).isEqualTo(5030);
    }

    @Test
    void testAmountOfObstructionPlacementsCausingALoop() {
        final Room room = PuzzleInputReader.readPuzzleInput("day06/day06_test_puzzleinput.txt");
        
        assertThat(room.getAmountOfObstructionPlacementsCausingALoop()).isEqualTo(6);
    }

    @Test
    void testDay06PuzzleSolutionToPart2() {
        final Room room = PuzzleInputReader.readPuzzleInput("day06/day06_puzzleinput.txt");

        assertThat(room.getAmountOfObstructionPlacementsCausingALoop()).isEqualTo(9999);
    }
}
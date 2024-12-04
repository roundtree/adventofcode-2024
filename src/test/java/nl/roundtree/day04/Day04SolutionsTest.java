package nl.roundtree.day04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day04SolutionsTest {

    @Test
    void testCountXmas() {
        final WordGrid wordGrid = PuzzleInputReader.readPuzzleInput("day04/day04_test_puzzleinput.txt");
        
        assertThat(wordGrid.countXmas()).isEqualTo(18);
    }

    @Test
    void testDay04PuzzleSolutionToPart1() {
        final WordGrid wordGrid = PuzzleInputReader.readPuzzleInput("day04/day04_puzzleinput.txt");

        assertThat(wordGrid.countXmas()).isEqualTo(2554);
    }
//
//    @Test
//    void setGetSumOfValidEnabledMultiplications() {
//        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_part2_test_puzzleinput.txt");
//
//        assertThat(new Calculator().sumOfAllValidEnabledMultiplications(instructionsLine)).isEqualTo(48);
//    }
//
//    @Test
//    void testDay04PuzzleSolutionToPart2() {
//        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_puzzleinput.txt");
//
//        assertThat(new Calculator().sumOfAllValidEnabledMultiplications(instructionsLine)).isEqualTo(98729041);
//    }
}
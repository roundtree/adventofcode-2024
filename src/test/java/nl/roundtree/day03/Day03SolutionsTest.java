package nl.roundtree.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day03SolutionsTest {

    @Test
    void testGetSumOfValidMultiplications() {
        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_part1_test_puzzleinput.txt");

        assertThat(new Calculator().sumOfAllValidMultiplications(instructionsLine)).isEqualTo(161);
    }

    @Test
    void testDay03PuzzleSolutionToPart1() {
        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_puzzleinput.txt");

        assertThat(new Calculator().sumOfAllValidMultiplications(instructionsLine)).isEqualTo(181345830);
    }

    @Test
    void setGetSumOfValidEnabledMultiplications() {
        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_part2_test_puzzleinput.txt");

        assertThat(new Calculator().sumOfAllValidEnabledMultiplications(instructionsLine)).isEqualTo(48);
    }

    @Test
    void testDay03PuzzleSolutionToPart2() {
        final String instructionsLine = PuzzleInputReader.readPuzzleInput("day03/day03_puzzleinput.txt");

        assertThat(new Calculator().sumOfAllValidEnabledMultiplications(instructionsLine)).isEqualTo(98729041);
    }
}
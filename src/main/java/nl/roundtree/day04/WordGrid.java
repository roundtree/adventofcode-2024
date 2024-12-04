package nl.roundtree.day04;

import java.util.ArrayList;
import java.util.List;

public record WordGrid(Letter[][] letterGrid) {
    
    public String getFourLettersInDirection(final Letter letter, final Direction direction) {
        final int rowDirection = direction.rowDirection;
        final int columnDirection = direction.columnDirection;

        final StringBuilder fourLetters = new StringBuilder("X");
        for (int i = 1; i < 4; i++) {
            try {
                fourLetters.append(letterGrid[letter.row + rowDirection * i][letter.column + columnDirection * i].character);
            } catch (final Exception e) {
                // out of bounds, break
                break;
            }

        }
        
        return fourLetters.toString();
    }

    public int countXmas() {
        int count = 0;
        
        for (int row = 0; row < letterGrid.length; row++) {
            for (int column = 0; column < letterGrid[row].length; column++) {
                final Letter letter = letterGrid[row][column];

                final List<String> surroundingWords = new ArrayList<>();
                if (letter.character == 'X') {
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.LEFT));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.TOP_LEFT));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.TOP));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.TOP_RIGHT));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.RIGHT));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.BOTTOM_RIGHT));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.BOTTOM));
                    surroundingWords.add(getFourLettersInDirection(letter, Direction.BOTTOM_LEFT));
                }

                count += (int) surroundingWords
                        .stream()
                        .filter(w -> w.equals("XMAS") || w.equals("SAMX"))
                        .count();
            }
        }

        return count;
    }

    public record Letter(char character,
                         int row,
                         int column) {
    }

    private enum Direction {
        LEFT(0, -1),
        TOP_LEFT(-1, -1),
        TOP(-1, 0),
        TOP_RIGHT(-1, 1),
        RIGHT(0, 1),
        BOTTOM_RIGHT(1, 1),
        BOTTOM(1, 0),
        BOTTOM_LEFT(1, -1);

        private final int rowDirection;
        private final int columnDirection;
        
        Direction(final int rowDirection, final int columnDirection) {
            this.rowDirection = rowDirection;
            this.columnDirection = columnDirection;
        }
    }
}



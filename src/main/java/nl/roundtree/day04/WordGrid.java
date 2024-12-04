package nl.roundtree.day04;

import java.util.ArrayList;
import java.util.List;

public record WordGrid(Letter[][] letterGrid) {
    
    public String getLettersInDirection(int amount, final Letter letter, final Direction direction) {
        final int rowDirection = direction.rowDirection;
        final int columnDirection = direction.columnDirection;

        final StringBuilder fourLetters = new StringBuilder("");
        for (int i = 0; i < amount; i++) {
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
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.LEFT));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.TOP_LEFT));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.TOP));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.TOP_RIGHT));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.RIGHT));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.BOTTOM_RIGHT));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.BOTTOM));
                    surroundingWords.add(getLettersInDirection(4, letter, Direction.BOTTOM_LEFT));
                }

                count += (int) surroundingWords
                        .stream()
                        .filter(w -> w.equals("XMAS") || w.equals("SAMX"))
                        .count();
            }
        }

        return count;
    }

    public int countCrossXmas() {
        int count = 0;

        for (int row = 0; row < letterGrid.length; row++) {
            for (int column = 0; column < letterGrid[row].length; column++) {
                final Letter letter = letterGrid[row][column];

                final List<String> surroundingWords = new ArrayList<>();
                if (letter.character == 'A') {
                    final String topLeftLetters = getLettersInDirection(2, letter, Direction.TOP_LEFT);
                    final String bottomRightLetters = getLettersInDirection(2, letter, Direction.BOTTOM_RIGHT);
                    surroundingWords.add(topLeftLetters.replace("A", "") + bottomRightLetters);


                    final String topRightLetters = getLettersInDirection(2, letter, Direction.TOP_RIGHT);
                    final String bottomLeftLetters = getLettersInDirection(2, letter, Direction.BOTTOM_LEFT);
                    surroundingWords.add(topRightLetters.replace("A", "") + bottomLeftLetters);
                }

                int masCount = (int) surroundingWords
                        .stream()
                        .filter(w -> w.equals("MAS") || w.equals("SAM"))
                        .count();
                
                if (masCount == 2) {
                    count += 1;
                }
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



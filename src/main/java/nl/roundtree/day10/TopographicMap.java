package nl.roundtree.day10;

import java.util.*;

public record TopographicMap(List<Position> positions) {

    public int sumOfTrailheadScores() {
        return getTrailheads()
                .stream()
                .mapToInt(t -> new HashSet<>(reachableNinePositions(t, new ArrayList<>())).size())
                .sum();
    }

    public int sumOfTrailheadRatings() {
        return getTrailheads()
                .stream()
                .mapToInt(t -> reachableNinePositions(t, new ArrayList<>()).size())
                .sum();
    }
    
    private List<Position> getTrailheads() {
        return positions
                .stream()
                .filter(p -> p.height == 0)
                .toList();
    }

    private List<Position> reachableNinePositions(final Position position, List<Position> foundNinePositions) {
        final List<Position> nextPositions = position.nextPositionsWithIncrementedHeight();
        for (final Position nextPosition : nextPositions) {
            if (nextPosition.height == 9) {
                foundNinePositions.add(nextPosition);
                foundNinePositions = reachableNinePositions(nextPosition, foundNinePositions);
            } else {
                foundNinePositions = reachableNinePositions(nextPosition, foundNinePositions);
            }
        }

        return foundNinePositions;
    }

    public static class Position {

        final int height;
        private final int row;
        private final int column;
        Position northPosition;
        Position eastPosition;
        Position southPosition;
        Position westPosition;

        public Position(final int height,
                        final int row,
                        final int column,
                        final Position northPosition,
                        final Position eastPosition,
                        final Position southPosition,
                        final Position westPosition) {
            this.height = height;
            this.row = row;
            this.column = column;
            this.northPosition = northPosition;
            this.eastPosition = eastPosition;
            this.southPosition = southPosition;
            this.westPosition = westPosition;
        }

        public List<Position> nextPositionsWithIncrementedHeight() {
            final List<Position> positions = new ArrayList<>();
            addIfOtherPositionHasIncrementedHeight(positions, northPosition);
            addIfOtherPositionHasIncrementedHeight(positions, eastPosition);
            addIfOtherPositionHasIncrementedHeight(positions, southPosition);
            addIfOtherPositionHasIncrementedHeight(positions, westPosition);
            return positions;
        }
        
        private void addIfOtherPositionHasIncrementedHeight(final List<Position> positions, final Position positionToCompare) {
            if (positionToCompare != null && positionToCompare.height == this.height + 1) {
                positions.add(positionToCompare);
            }
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Position position = (Position) o;
            return height == position.height && row == position.row && column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(height, row, column);
        }
    }
}

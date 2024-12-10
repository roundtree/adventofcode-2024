package nl.roundtree.day10;

import java.util.*;

public record TopographicMap(List<Position> positions) {

    public int sumOfTrailheadScores() {
        final List<Position> trailheads = positions
                .stream()
                .filter(p -> p.height == 0)
                .toList();

        return trailheads
                .stream()
                .mapToInt(t -> new HashSet<>(reachableNinePositions(t, 0, new ArrayList<>())).size())
                .sum();
    }

    public int sumOfTrailheadRatings() {
        final List<Position> trailheads = positions
                .stream()
                .filter(p -> p.height == 0)
                .toList();

        return trailheads
                .stream()
                .mapToInt(t -> reachableNinePositions(t, 0, new ArrayList<>()).size())
                .sum();
    }

    private List<Position> reachableNinePositions(final Position position, int numberOfNinePositions, List<Position> foundNinePositions) {
        final List<Position> nextPositions = position.nextPositionsWithIncrementedHeight();
        for (final Position nextPosition : nextPositions) {
            if (nextPosition.height == 9) {
                foundNinePositions.add(nextPosition);
                foundNinePositions = reachableNinePositions(nextPosition, numberOfNinePositions + 1, foundNinePositions);
            } else {
                foundNinePositions = reachableNinePositions(nextPosition, numberOfNinePositions, foundNinePositions);
            }
        }

        return foundNinePositions;
    }

    public static class Position {

        private final int height;
        private final int row;
        private final int column;
        private Position northPosition;
        private Position eastPosition;
        private Position southPosition;
        private Position westPosition;

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
            if (northPosition != null && northPosition.height == this.height + 1) {
                positions.add(northPosition);
            }

            if (eastPosition != null && eastPosition.height == this.height + 1) {
                positions.add(eastPosition);
            }

            if (southPosition != null && southPosition.height == this.height + 1) {
                positions.add(southPosition);
            }

            if (westPosition != null && westPosition.height == this.height + 1) {
                positions.add(westPosition);
            }

            return positions;
        }

        public void setNorthPosition(final Position northPosition) {
            this.northPosition = northPosition;
        }

        public void setEastPosition(final Position eastPosition) {
            this.eastPosition = eastPosition;
        }

        public void setSouthPosition(final Position southPosition) {
            this.southPosition = southPosition;
        }

        public void setWestPosition(final Position westPosition) {
            this.westPosition = westPosition;
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

        @Override
        public String toString() {
            return "Position{" +
                   "height=" + height +
                   ", row=" + row +
                   ", column=" + column +
                   '}';
        }
    }
}

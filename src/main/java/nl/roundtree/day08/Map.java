package nl.roundtree.day08;

import java.util.*;

public record Map(List<Antenna> antennas, int rowExclusiveBound, int columnExclusiveBound) {
    
    public List<Antenna> findMatchingAntennas(final Antenna startAntenna) {
        return antennas
                .stream()
                .filter(a -> !a.equals(startAntenna))
                .filter(a -> a.matchesFrequency(startAntenna))
                .toList();
    }

    public int countAntinodes() {
        final Set<Position> antinodes = new HashSet<>();
        
        for (final Antenna antenna : antennas) {
            final List<Antenna> matchingAntennas = findMatchingAntennas(antenna);
            
            for (final Antenna matchingAntenna : matchingAntennas) {
                int antinodeRowOffset = antenna.getInverseRowDistanceTo(matchingAntenna);
                int antinodeColumnOffset = antenna.getInverseColumnDistanceTo(matchingAntenna);
                
                final Position antinode = new Position(antenna.position.row() + antinodeRowOffset, antenna.position.column() + antinodeColumnOffset);
                if (antinode.withinBounds(rowExclusiveBound, columnExclusiveBound)) {
                    antinodes.add(antinode);
                }
            }
        }

        return antinodes.size();
    }

    public record Position(int row, int column){
        
        public boolean withinBounds(final int maxRowPosition, final int maxColumnPosition) {
            return row >= 0 && row < maxRowPosition && column >= 0 && column < maxColumnPosition;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Position position = (Position) o;
            return row == position.row && column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }

    public record Antenna(Position position, String frequency) {

        public boolean matchesFrequency(final Antenna other) {
            return this.frequency.equals(other.frequency);
        }
        
        public int getInverseRowDistanceTo(final Antenna other) {
            final int distance = Math.abs(this.position.row() - other.position.row());
            if (this.position.row() < other.position.row()) {
                return -distance;
            }
            
            return distance;
        }

        public int getInverseColumnDistanceTo(final Antenna other) {
            final int distance = Math.abs(this.position.column() - other.position.column());
            if (this.position.column() < other.position.column()) {
                // This antenna is more to the left, negate the columnDistance
                return -distance;
            }
            
            return distance;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Antenna antenna = (Antenna) o;
            return Objects.equals(frequency, antenna.frequency) && Objects.equals(position, antenna.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, frequency);
        }
    }
}

package nl.roundtree.day08;

import java.util.*;
import java.util.stream.Collectors;

public record Map(List<Antenna> antennas, Bounds bounds) {

    public int countAntinodes() {
        return antennas
                .stream()
                .map(antenna -> findMatchingAntennas(antenna)
                        .stream()
                        .map(matchingAntenna -> antenna.position.getAntinodePosition(matchingAntenna.position, bounds))
                        .toList())
                .flatMap(Collection::stream)
                .<Position>mapMulti(Optional::ifPresent)
                .collect(Collectors.toCollection(HashSet::new))
                .size();
    }

    public int countRepeatingAntinodes() {
        return antennas
                .stream()
                .map(antenna ->
                        findMatchingAntennas(antenna)
                                .stream()
                                .map(a -> a.position)
                                .map(matchingAntennaPosition -> {
                                    final Set<Position> antinodes = new HashSet<>();
                                    antinodes.add(antenna.position);

                                    int antinodeRowOffset = antenna.position.getInverseRowDistanceTo(matchingAntennaPosition);
                                    int antinodeColumnOffset = antenna.position.getInverseColumnDistanceTo(matchingAntennaPosition);

                                    Optional<Position> antinode;
                                    Position currentPosition = antenna.position;
                                    do {
                                        antinode = currentPosition.getAntinodePosition(antinodeRowOffset, antinodeColumnOffset, bounds);
                                        antinode.ifPresent(antinodes::add);
                                        currentPosition = antinode.orElse(null);
                                    } while (antinode.isPresent());

                                    return antinodes;
                                })
                                .flatMap(Collection::stream)
                                .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new))
                .size();
    }

    private List<Antenna> findMatchingAntennas(final Antenna startAntenna) {
        return antennas
                .stream()
                .filter(a -> !a.equals(startAntenna))
                .filter(a -> a.matchesFrequency(startAntenna))
                .toList();
    }

    public record Position(int row, int column) {
        
        public Optional<Position> getAntinodePosition(final Position targetPosition, final Bounds bounds) {
            int antinodeRowOffset = this.getInverseRowDistanceTo(targetPosition);
            int antinodeColumnOffset = this.getInverseColumnDistanceTo(targetPosition);

            return getAntinodePosition(antinodeRowOffset, antinodeColumnOffset, bounds);
        }

        public Optional<Position> getAntinodePosition(final int antinodeRowOffset, final int antinodeColumnOffset, final Bounds bounds) {
            final Position antinode = new Position(this.row + antinodeRowOffset, this.column + antinodeColumnOffset);
            return bounds.isPositionWithin(antinode)
                    ? Optional.of(antinode)
                    : Optional.empty();
        }

        public int getInverseRowDistanceTo(final Position other) {
            final int distance = Math.abs(this.row - other.row);
            if (this.row < other.row) {
                // This position is more to the top, negate the row distance
                return -distance;
            }

            return distance;
        }

        public int getInverseColumnDistanceTo(final Position other) {
            final int distance = Math.abs(this.column - other.column);
            if (this.column < other.column) {
                // This position is more to the left, negate the column distance
                return -distance;
            }

            return distance;
        }
    }

    public record Antenna(Position position, String frequency) {

        public boolean matchesFrequency(final Antenna other) {
            return this.frequency.equals(other.frequency);
        }
    }
    
    public record Bounds(int maxRowExclusive, int maxColumnExclusive) {
        
        boolean isPositionWithin(final Position position) {
            return position.row() >= 0 && position.row() < maxRowExclusive && position.column() >= 0 && position.column() < maxColumnExclusive;
        }
    }
}

package nl.roundtree.day06;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

public record Room(Position[][] map,
                   Guard guard) {

    public int getNumberOfStepsBeforeLeavingRoom() {
        Stop stop;
        do {
            stop = guard.moveToNextStop(this);
            guard.changeDirection();
        } while (stop != null);

        return guard.getNumberOfVisitedPositions();
    }

    public int getAmountOfObstructionPlacementsCausingALoop() {
        int count = 0;

        for (int row = 0; row < map.length; row++) {
            final Position[] columns = map[row];
            for (int column = 0; column < columns.length; column++) {
                final Position originalPosition = map[row][column];
                if (originalPosition.type != PositionType.OBSTACLE && !originalPosition.equals(guard.startPosition)) {
                    map[row][column] = new Position(originalPosition.row, originalPosition.column(), PositionType.OBSTACLE);

                    Stop stop;
                    do {
                        try {
                            stop = guard.moveToNextStop(this);
                            guard.changeDirection();
                        } catch (final LoopingThroughTheRoomException e) {
                            count++;
                            map[row][column] = originalPosition;
                            guard.resetToStart();
                            break;
                        }
                    } while (stop != null);

                    map[row][column] = originalPosition;
                    guard.resetToStart();
                }
            }
        }

        return count;
    }

    private Position getPositionNextToInDirection(final Position currentPosition,
                                                  final Direction currentDirection) {
        try {
            final int nextRowPosition = currentPosition.row + currentDirection.rowDirection;
            final int nextColumnPosition = currentPosition.column + currentDirection.columnDirection;
            return map[nextRowPosition][nextColumnPosition];
        } catch (final Exception e) {
            // out of bounds, which means outside of room
            return null;
        }
    }

    public record Position(int row,
                           int column,
                           PositionType type) {

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Position position = (Position) o;
            return row == position.row && column == position.column && type == position.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column, type);
        }
    }

    public static class Guard {

        private final Position startPosition;
        private final Direction startDirection;
        private Position currentPosition;
        private Direction currentDirection;

        private final Set<Position> visitedPositions = new HashSet<>();
        private final Set<Stop> stops = new HashSet<>();

        public Guard(final Position startPosition, final Direction startDirection) {
            this.startPosition = startPosition;
            this.startDirection = startDirection;
            this.currentPosition = startPosition;
            this.currentDirection = startDirection;
            visitedPositions.add(startPosition);
        }

        Stop moveToNextStop(final Room room) {
            final Position obstacle = moveToNextPositionUntilObstacle(room);
            Stop stop = null;

            if (nonNull(obstacle)) {
                stop = new Stop(currentPosition, currentDirection);
                if (stops.contains(stop)) {
                    throw new LoopingThroughTheRoomException();
                } else {
                    stops.add(stop);
                }
            }

            return stop;
        }

        Position moveToNextPositionUntilObstacle(final Room room) throws LoopingThroughTheRoomException {
            Position nextPosition = room.getPositionNextToInDirection(currentPosition, currentDirection);
            while (nonNull(nextPosition) && nextPosition.type != PositionType.OBSTACLE) {
                currentPosition = nextPosition;
                visitedPositions.add(currentPosition);
                nextPosition = moveToNextPositionUntilObstacle(room);
            }

            return nextPosition;
        }

        void changeDirection() {
            currentDirection = currentDirection.getDirectionToRight();
        }

        int getNumberOfVisitedPositions() {
            return visitedPositions.size();
        }

        void resetToStart() {
            currentPosition = startPosition;
            currentDirection = startDirection;
            stops.clear();
        }
    }

    public enum PositionType {
        OBSTACLE,
        EMPTY
    }

    private record Stop(Position position, Direction direction) {

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Stop stop = (Stop) o;
            return Objects.equals(position, stop.position) && direction == stop.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, direction);
        }
    }
    
    public enum Direction {
        LEFT(0, -1) {
            public Direction getDirectionToRight() {
                return UP;
            }
        },
        UP(-1, 0) {
            public Direction getDirectionToRight() {
                return RIGHT;
            }
        },
        RIGHT(0, 1) {
            @Override
            Direction getDirectionToRight() {
                return DOWN;
            }
        },
        DOWN(1, 0) {
            @Override
            Direction getDirectionToRight() {
                return LEFT;
            }
        };

        private final int rowDirection;
        private final int columnDirection;

        Direction(final int rowDirection, final int columnDirection) {
            this.rowDirection = rowDirection;
            this.columnDirection = columnDirection;
        }

        abstract Direction getDirectionToRight();
    }

    private static class LoopingThroughTheRoomException extends RuntimeException {
    }
}

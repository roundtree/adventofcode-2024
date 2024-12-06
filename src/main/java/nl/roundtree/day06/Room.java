package nl.roundtree.day06;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

public record Room(Position[][] map,
                   Guard guard) {
    
    public int getNumberOfStepsBeforeLeavingRoom() {
        Position positionBeforeObstacle = guard.moveToNextPositionUntilObstacle(this);
        while(positionBeforeObstacle != null) {
            guard.changeDirection();
            positionBeforeObstacle = guard.moveToNextPositionUntilObstacle(this);
        }

        return guard.getNumberOfVisitedPositions();
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

    public int getAmountOfObstructionPlacementsCausingALoop() {
        return 0; // TODO: implement
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

    public enum PositionType {
        OBSTACLE,
        EMPTY
    }

    public static class Guard {

        private Position currentPosition;
        private Direction currentDirection;
        private int numberOfSteps = 0;
        
        private Set<Position> visitedPositions = new HashSet<>();

        public Guard(final Position currentPosition, final Direction currentDirection) {
            this.currentPosition = currentPosition;
            this.currentDirection = currentDirection;
            visitedPositions.add(currentPosition);
        }

        public Position moveToNextPositionUntilObstacle(final Room room) {
            Position nextPosition = room.getPositionNextToInDirection(currentPosition, currentDirection);
            while (nonNull(nextPosition) && nextPosition.type != PositionType.OBSTACLE) {
                currentPosition = nextPosition;
                visitedPositions.add(currentPosition);
                nextPosition = moveToNextPositionUntilObstacle(room);
            }
            
            return nextPosition;
        }
        
        public void changeDirection() {
            currentDirection = currentDirection.getDirectionToRight();
        }
        
        public int getNumberOfVisitedPositions() {
            return visitedPositions.size();
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
}

package nl.roundtree.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PuzzleInputReader {

    static Room readPuzzleInput(final String path) {
        final List<Room.Position> positions = new ArrayList<>();
        Room.Guard guard = null;
        
        int numberOfRows = 0;
        int numberOfColumns = 0;

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();
            numberOfColumns = line.length();

            while (line != null) {
                final String[] splittedLine = line.split("");
                for (int columnNumber = 0; columnNumber < splittedLine.length; columnNumber++) {
                    final String character = splittedLine[columnNumber];
                    if (character.equals(".")) {
                        positions.add(new Room.Position(numberOfRows, columnNumber, Room.PositionType.EMPTY));
                    } else if (character.equals("#")) {
                        positions.add(new Room.Position(numberOfRows, columnNumber, Room.PositionType.OBSTACLE));
                    } else {
                        final Room.Position position = new Room.Position(numberOfRows, columnNumber, Room.PositionType.EMPTY);
                        positions.add(position);

                        Room.Direction direction = switch (character) {
                            case ">" -> Room.Direction.RIGHT;
                            case "v" -> Room.Direction.DOWN;
                            case "<" -> Room.Direction.LEFT;
                            case "^" -> Room.Direction.UP;
                            default -> null;
                        };

                        guard = new Room.Guard(position, direction);
                    }
                }

                line = reader.readLine();
                numberOfRows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Room.Position[][] map = new Room.Position[numberOfRows][numberOfColumns];
        positions.forEach(position -> map[position.row()][position.column()] = position);
        return new Room(map, guard);
    }
}

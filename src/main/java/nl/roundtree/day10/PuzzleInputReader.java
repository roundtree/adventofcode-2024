package nl.roundtree.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PuzzleInputReader {

    static TopographicMap readPuzzleInput(final String path) {
        final List<TopographicMap.Position> positions = new ArrayList<>();
        
        final TopographicMap.Position[][] positionMap;

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();
            positionMap =  new TopographicMap.Position[line.length()][line.length()];
            
            int rowNumber = 0;

            while (line != null) {
                final String[] splittedLine = line.split("");
                
                for (int columnNumber = 0; columnNumber < splittedLine.length; columnNumber++) {
                    final int height = Integer.parseInt(splittedLine[columnNumber]);
                    final TopographicMap.Position northPosition = getPositionAtIndex(positionMap, rowNumber - 1, columnNumber);
                    final TopographicMap.Position eastPosition = getPositionAtIndex(positionMap, rowNumber, columnNumber + 1);
                    final TopographicMap.Position southPosition = getPositionAtIndex(positionMap, rowNumber + 1, columnNumber);
                    final TopographicMap.Position westPosition = getPositionAtIndex(positionMap, rowNumber, columnNumber - 1);

                    final TopographicMap.Position position = new TopographicMap.Position(height, rowNumber, columnNumber, northPosition, eastPosition, southPosition, westPosition);
                    positionMap[rowNumber][columnNumber] = position;
                    
                    if (northPosition != null) {
                        northPosition.southPosition = position;
                    }
                    
                    if (westPosition != null) {
                        westPosition.eastPosition = position;
                    }
                    
                    positions.add(position);
                }

                line = reader.readLine();
                rowNumber++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TopographicMap(positions);
    }
    
    private static TopographicMap.Position getPositionAtIndex(final TopographicMap.Position[][] map, final int row, final int column) {
        try {
            return map[row][column];
        } catch (final Exception e) {
            return null;
        }
    }
}

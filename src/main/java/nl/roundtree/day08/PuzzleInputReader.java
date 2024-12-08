package nl.roundtree.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PuzzleInputReader {

    static Map readPuzzleInput(final String path) {
        final List<Map.Antenna> antennas = new ArrayList<>();
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
                    if (!character.equals(".")) {
                        antennas.add(new Map.Antenna(new Map.Position(numberOfRows, columnNumber), character));
                    }
                }

                line = reader.readLine();
                numberOfRows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Map(antennas, new Map.Bounds(numberOfRows, numberOfColumns));
    }
}

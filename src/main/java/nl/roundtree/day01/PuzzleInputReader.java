package nl.roundtree.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

class PuzzleInputReader {

    static Locations readPuzzleInput(final String path) {
        final List<Location> leftLocations = new ArrayList<>();
        final List<Location> rightLocations = new ArrayList<>();

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();

            while (line != null) {
                String[] splittedLine = line.split(" {3}");
                leftLocations.add(new Location(Integer.parseInt(splittedLine[0])));
                rightLocations.add(new Location(Integer.parseInt(splittedLine[1])));

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Locations(leftLocations, rightLocations);
    }
}

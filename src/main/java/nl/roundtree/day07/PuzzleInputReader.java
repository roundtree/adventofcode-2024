package nl.roundtree.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PuzzleInputReader {

    static Calibration readPuzzleInput(final String path) {
        final List<Calibration.Equation> equations = new ArrayList<>();

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line =  reader.readLine();

            while (line != null) {
                final String[] splittedLine = line.split(":");
                final Long testValue = Long.parseLong(splittedLine[0]);

                final List<Integer> numbers = Stream.of(splittedLine[1].trim().split(" "))
                        .map(Integer::parseInt)
                        .toList();

                equations.add(new Calibration.Equation(testValue, numbers));

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Calibration(equations);
    }
}

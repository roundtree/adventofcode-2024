package nl.roundtree.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PuzzleInputReader {

    static Reports readPuzzleInput(final String path) {
        final List<Report> reports = new ArrayList<>();

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();

            while (line != null) {
                String[] splittedLine = line.split(" ");

                final List<Level> levels = Stream.of(splittedLine)
                        .map(s -> new Level(Integer.valueOf(s)))
                        .toList();
                reports.add(new Report(levels));

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Reports(reports);
    }
}

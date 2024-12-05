package nl.roundtree.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class PuzzleInputReader {

    static PrintRequest readPuzzleInput(final String path) {
        final Map<Integer, List<Integer>> mustBeBeforeMap = new HashMap<>();
        final Map<Integer, List<Integer>> mustBeAfterMap = new HashMap<>();
        final List<PrintRequest.Update> updates = new ArrayList<>();
        
        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();
            
            while (line != null) {
                if (!line.isBlank()) {
                    if (line.contains("|")) {
                        final String[] splittedLine = line.split("\\|");
                        int left = Integer.parseInt(splittedLine[0]);
                        int right = Integer.parseInt(splittedLine[1]);

                        final List<Integer> numbersMustBeToTheRight = mustBeBeforeMap.getOrDefault(left, new ArrayList<>());
                        numbersMustBeToTheRight.add(right);
                        mustBeBeforeMap.put(left, numbersMustBeToTheRight);

                        final List<Integer> numbersMustBeToTheLeft = mustBeAfterMap.getOrDefault(right, new ArrayList<>());
                        numbersMustBeToTheLeft.add(left);
                        mustBeAfterMap.put(right, numbersMustBeToTheLeft);
                    } else {
                        final List<Integer> numbers =  Stream.of(line.split(","))
                                .map(Integer::parseInt)
                                .toList();
                        updates.add(new PrintRequest.Update(numbers));
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PrintRequest(mustBeBeforeMap, mustBeAfterMap, updates);
    }
}

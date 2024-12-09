package nl.roundtree.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PuzzleInputReader {

    static DiskMap readPuzzleInput(final String path) {
        final List<DiskMap.Space> spaces = new ArrayList<>();

        try (final InputStream inputStream = PuzzleInputReader.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();
            final String[] characters = line.split("");
            
            
            boolean currentIsFile = true;
            long idCounter = 0;
            for (String character : characters) {
                final int numberOfSpaces = Integer.parseInt(character);
                if (currentIsFile) {
                    for (int i = 0; i < numberOfSpaces; i++) {
                        spaces.add(new DiskMap.Space(new DiskMap.File(idCounter)));
                    }
                    
                    idCounter++;
                    currentIsFile = false;
                } else {
                    for (int i = 0; i < numberOfSpaces; i++) {
                        spaces.add(new DiskMap.Space());
                    }
                    
                    currentIsFile = true;
                }
            }
            
            int breakp = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new DiskMap(spaces);
    }
}

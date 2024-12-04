package nl.roundtree.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PuzzleInputReader {

    static WordGrid readPuzzleInput(final String path) {
        final List<WordGrid.Letter> letters = new ArrayList<>();
        
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
                    letters.add(new WordGrid.Letter(splittedLine[columnNumber].charAt(0), numberOfRows, columnNumber));
                }
                
                line = reader.readLine();
                numberOfRows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final WordGrid.Letter[][] letterGrid = new WordGrid.Letter[numberOfRows][numberOfColumns];
        letters.forEach(letter -> letterGrid[letter.row()][letter.column()] = letter);
        return new WordGrid(letterGrid);
    }
}

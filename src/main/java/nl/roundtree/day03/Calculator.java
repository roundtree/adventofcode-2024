package nl.roundtree.day03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Calculator {

    int sumOfAllValidMultiplications(final String instructionLine) {
        final Pattern pattern = Pattern.compile("mul\\(([\\d]*,*[\\d]*)*\\)");
        final Matcher matcher = pattern.matcher(instructionLine);

        int sum = 0;
        while (matcher.find()) {
            final String matchedPart = matcher.group()
                    .replace("mul(", "")
                    .replace(")", "");

            sum += Stream.of(matchedPart.split(","))
                    .map(Integer::valueOf)
                    .reduce(1, (a, b) -> a * b);
        }

        return sum;
    }

    int sumOfAllValidEnabledMultiplications(final String instructionLine) {
        final Matcher doMatcher = Pattern.compile("do\\(\\)").matcher(instructionLine);
        final Matcher dontMatcher = Pattern.compile("don't\\(\\)").matcher(instructionLine);
        
        final List<Index> doIndexes = doMatcher.results()
                .map(r -> new Index(r.start(), Command.DO))
                .toList();
        
        final List<Index> dontIndexes = dontMatcher.results()
                .map(r -> new Index(r.end(), Command.DONT))
                .toList();
        
        final List<Index> indexes = new ArrayList<>();
        indexes.addAll(doIndexes);
        indexes.addAll(dontIndexes);

        final List<Index> sortedPositions = indexes
                .stream()
                .sorted(Comparator.comparing(Index::position))
                .toList();

        final String redactedInstructionLine = redactDontParts(instructionLine, sortedPositions);
        return sumOfAllValidMultiplications(redactedInstructionLine);
    }

    private static String redactDontParts(final String instructionLine, final List<Index> sortedPositions) {
        final List<Range> rangesToRemove = new ArrayList<>();
        Integer currentRangeStart = null;
        for (int i = 0; i < sortedPositions.size(); i++) {
            final Index index = sortedPositions.get(i);
            if (index.command == Command.DONT && currentRangeStart == null) {
                currentRangeStart = index.position;
            } else if ((index.command == Command.DO && currentRangeStart != null)) {
                rangesToRemove.add(new Range(currentRangeStart, index.position));
                currentRangeStart = null;
            } else if (currentRangeStart != null && i == sortedPositions.size() - 1) {
                rangesToRemove.add(new Range(currentRangeStart, instructionLine.length()));
            }
        }

        final StringBuilder builder = new StringBuilder(instructionLine);
        rangesToRemove.forEach(range -> {
            // Create a string with empty characters to use for replacing, maintains original length of the input line
            final String emptyCharacters = new String(new char[range.end - range.start]);
            builder.replace(range.start, range.end, emptyCharacters);
        });

        return builder.toString();
    }

    private record Range(int start, int end) {
    }
    
    private record Index(int position, Command command) {
    }
    
    private enum Command {
        DO,
        DONT
    }
}

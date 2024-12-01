package nl.roundtree.day01;

import java.util.List;

public record Locations(List<Location> leftLocationList,
                        List<Location> rightLocationList) {

    public int sumOfAllLocationDistances() {
        int sum = 0;

        final List<Location> sortedLeftLocationList = leftLocationList.stream().sorted().toList();
        final List<Location> sortedRightLocationList = rightLocationList.stream().sorted().toList();

        for (int i = 0; i < sortedLeftLocationList.size(); i++) {
            final Location leftLocation = sortedLeftLocationList.get(i);
            final Location rightLocation = sortedRightLocationList.get(i);
            sum += leftLocation.getDistanceTo(rightLocation);
        }

        return sum;
    }

    public int calculateSimilarityScore() {
        int similarityScore = 0;

        for (final Location location : leftLocationList) {
            similarityScore += (int) (location.locationId() * rightLocationList
                    .stream()
                    .filter(l -> location.locationId() == l.locationId())
                    .count());
        }

        return similarityScore;
    }
}

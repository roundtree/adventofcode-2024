package nl.roundtree.day01;

public record Location(int locationId) implements Comparable<Location> {


    @Override
    public int compareTo(Location o) {
        if (this.locationId == o.locationId) {
            return 0;
        }

        return this.locationId < o.locationId ? -1 : 1;
    }

    public int getDistanceTo(final Location otherLocation) {
        return Math.abs(this.locationId() - otherLocation.locationId());
    }
}

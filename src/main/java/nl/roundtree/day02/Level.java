package nl.roundtree.day02;

public record Level(int number) {

    public boolean isDifferenceWithinBounds(final int lowerBound, final int upperBound, final Level levelToCompare) {
        final int difference = Math.abs(this.number - levelToCompare.number);
        return difference >= lowerBound && difference <= upperBound;
    }
    
    public SequenceMode increasingOrDecreasing(final Level levelToCompare) {
        if (levelToCompare.number > this.number) {
            return SequenceMode.INCREASING;
        } else if (levelToCompare.number == this.number) {
            return SequenceMode.EQUAL;
        } else {
            return SequenceMode.DECREASING;
        }
    }
}

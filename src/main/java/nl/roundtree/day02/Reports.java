package nl.roundtree.day02;

import java.util.List;

public record Reports(List<Report> reports) {

    public int getAmountOfSafeReports() {
        return (int) reports
                .stream()
                .filter(Report::isSafe)
                .count();
    }

    public int getAmountOfSafeReportsWithDampening() {
        return (int) reports
                .stream()
                .filter(Report::isSafeWithDampening)
                .count();
    }
}

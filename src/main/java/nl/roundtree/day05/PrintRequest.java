package nl.roundtree.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record PrintRequest(Map<Integer, List<Integer>> rightPagesByPageNumber,
                           Map<Integer, List<Integer>> leftPagesByPageNumber,
                           List<Update> updates) {

    public int sumOfMiddlePageNumbersOfValidUpdates() {
        int sum = 0;

        for (final Update update : updates) {
            if (update.isValid(rightPagesByPageNumber, leftPagesByPageNumber)) {
                sum += update.getMiddlePageNumber();
            }
        }

        return sum;
    }

    public int sumOfMiddlePageNumbersOfCorrectedUpdates() {
        int sum = 0;

        for (final Update update : updates) {
            if (!update.isValid(rightPagesByPageNumber, leftPagesByPageNumber)) {
                final Update correctedUpdate = update.getCorrectedUpdate(rightPagesByPageNumber, leftPagesByPageNumber);
                sum += correctedUpdate.getMiddlePageNumber();
            }
        }

        return sum;
    }

    public record Update(List<Integer> pageNumbers) {

        public boolean isValid(final Map<Integer, List<Integer>> rightPagesByPageNumber,
                               final Map<Integer, List<Integer>> leftPagesByPageNumber) {
            for (int i = 0; i < pageNumbers.size(); i++) {
                final Integer currentPageNumber = pageNumbers.get(i);
                final List<Integer> rightPages = rightPagesByPageNumber.getOrDefault(currentPageNumber, List.of());

                if (!rightPages.isEmpty()) {
                    for (int p = 0; p < i; p++) {
                        if (rightPages.contains(pageNumbers.get(p))) {
                            return false;
                        }
                    }
                }

                final List<Integer> leftPages = leftPagesByPageNumber.getOrDefault(currentPageNumber, List.of());
                if (!leftPages.isEmpty()) {
                    for (int p = i + 1; p < pageNumbers.size(); p++) {
                        if (leftPages.contains(pageNumbers.get(p))) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }


        public Update getCorrectedUpdate(final Map<Integer, List<Integer>> rightPages,
                                         final Map<Integer, List<Integer>> leftPages) {
            final List<Integer> correctedPageNumbers = new ArrayList<>(pageNumbers);

            int index = 0;
            while (index < correctedPageNumbers.size()) {
                index = correctPageFromIndex(index, correctedPageNumbers, rightPages, leftPages);
            }

            return new Update(correctedPageNumbers);
        }

        private int correctPageFromIndex(final int startIndex,
                                         final List<Integer> pageNumbers,
                                         final Map<Integer, List<Integer>> rightPagesByPageNumber,
                                         final Map<Integer, List<Integer>> leftPagesByPageNumber) {

            int index = startIndex;
            while (index < pageNumbers.size()) {
                final Integer currentPageNumber = pageNumbers.get(index);
                final List<Integer> rightPages = rightPagesByPageNumber.getOrDefault(currentPageNumber, List.of());

                // Correct a single page by moving it to the right
                if (!rightPages.isEmpty()) {
                    for (int p = 0; p < index; p++) {
                        final Integer currentPageToTheLeft = pageNumbers.get(p);
                        if (rightPages.contains(currentPageToTheLeft)) {
                            pageNumbers.remove(currentPageToTheLeft);
                            pageNumbers.add(p + 1 < pageNumbers.size() ? p + 1 : pageNumbers.size() - 1, currentPageToTheLeft);
                            return 0; // moved a page, reset loop
                        }
                    }
                }

                final List<Integer> leftPages = leftPagesByPageNumber.getOrDefault(currentPageNumber, List.of());
                if (!leftPages.isEmpty()) {
                    for (int p = index + 1; p < pageNumbers.size(); p++) {
                        final Integer currentPageToTheRight = pageNumbers.get(p);
                        if (leftPages.contains(currentPageToTheRight)) {
                            pageNumbers.remove(currentPageToTheRight);
                            pageNumbers.add((p > 0 ? p - 1 : 0), currentPageToTheRight);
                            return 0; // moved a page, reset loop
                        }
                    }
                }

                index++;
            }

            return index;
        }

        public int getMiddlePageNumber() {
            return pageNumbers.get((pageNumbers.size() - 1) / 2);
        }
    }
}


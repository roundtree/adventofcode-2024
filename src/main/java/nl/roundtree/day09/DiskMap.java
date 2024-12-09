package nl.roundtree.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record DiskMap(List<Space> spaces) {

    public Long calculateChecksum() {
        final List<Space> spaces = new ArrayList<>(this.spaces);

        final List<Space> emptySpaces = spaces
                .stream()
                .filter(Space::isEmpty)
                .collect(Collectors.toCollection(ArrayList::new));

        final List<Space> fileSpaces = spaces
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        for (final Space emptySpace : emptySpaces) {
            final Space lastFile = fileSpaces.getLast();

            if (lastFile != null && emptySpace.originalIndex < lastFile.originalIndex) {
                spaces.remove((int) emptySpace.originalIndex);
                spaces.add((int) emptySpace.originalIndex, lastFile);
                spaces.remove((int) lastFile.originalIndex);
                fileSpaces.removeLast();
            } else {
                spaces.removeLast();
            }
        }
        
        long checksum = 0L;
        for (int i = 0; i < spaces.size(); i++) {
            checksum += i * spaces.get(i).file.id;
        }
        
        return checksum;
    }

    public static class Space {
        
        private File file;
        private final long originalIndex;
        
        public Space(long originalIndex) {
            this.originalIndex = originalIndex;
        }

        public Space(long originalIndex, final File file) {
            this.file = file;
            this.originalIndex = originalIndex;
        }

        public boolean isEmpty() {
            return file == null;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Space space = (Space) o;
            return originalIndex == space.originalIndex && Objects.equals(file, space.file);
        }

        @Override
        public int hashCode() {
            return Objects.hash(file, originalIndex);
        }

        @Override
        public String toString() {
            return "Space{" +
                   "file=" + file +
                   ", id=" + originalIndex +
                   '}';
        }
    }
    
    public record File(Long id) {
    }
}

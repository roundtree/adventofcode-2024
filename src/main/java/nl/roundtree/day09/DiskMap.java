package nl.roundtree.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
        
        while (!emptySpaces.isEmpty()) {
            final Space lastFileSpace = fileSpaces.getLast();
            final Space firstEmptySpace = emptySpaces.getFirst();
            final int firstEmptySpaceIndex = spaces.indexOf(firstEmptySpace);
            spaces.remove(firstEmptySpaceIndex);
            spaces.remove(lastFileSpace);
            spaces.add(firstEmptySpaceIndex, lastFileSpace);
            fileSpaces.remove(lastFileSpace);
            emptySpaces.remove(emptySpaces.getFirst());
            
            if (!emptySpaces.isEmpty() && spaces.indexOf(emptySpaces.getFirst()) > spaces.indexOf(fileSpaces.getLast())) {
                break;
            }
        }
        
        spaces.removeAll(emptySpaces);
        
        long checksum = 0L;
        for (int i = 0; i < spaces.size(); i++) {
            checksum += i * spaces.get(i).file.id;
        }
        
        return checksum;
    }

    public static class Space {
        
        private File file;
        private final String uuid;
        
        public Space() {
            this.uuid = UUID.randomUUID().toString();
        }

        public Space(final File file) {
            this.file = file;
            this.uuid = UUID.randomUUID().toString();
        }

        public boolean isEmpty() {
            return file == null;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Space space = (Space) o;
            return Objects.equals(file, space.file) && Objects.equals(uuid, space.uuid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(file, uuid);
        }

        @Override
        public String toString() {
            return "Space{" +
                   "file=" + file +
                   ", uuid='" + uuid + '\'' +
                   '}';
        }
    }
    
    public record File(Long id) {
    }
}

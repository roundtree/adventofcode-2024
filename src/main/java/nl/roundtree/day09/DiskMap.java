package nl.roundtree.day09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record DiskMap(List<SpaceBlock> spaceBlocks) {

    public Long calculateChecksumMovingFiles() {
        final List<Space> spaces = getAllSpaces(spaceBlocks);
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

    public Long calculateChecksumMovingFileBlocks() {
        final List<SpaceBlock> spaceBlocks = new ArrayList<>(this.spaceBlocks);
        
        final List<SpaceBlock> fileSpaceBlocks = spaceBlocks
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new))
                .reversed();
        
        for (final SpaceBlock fileSpaceBlock : fileSpaceBlocks) {
            for (int i = 0; i < fileSpaceBlock.originalIndex; i++) {
                final SpaceBlock spaceBlock = spaceBlocks.get(i);
                if (fileSpaceBlock.fitsIn(spaceBlock)) {
                    spaceBlock.moveBlockInThisBlock(fileSpaceBlock);
                    break;
                }
            }
        }
        
        final List<Space> spaces = getAllSpaces(spaceBlocks);
        long checksum = 0L;
        for (int i = 0; i < spaces.size(); i++) {
            final Space space = spaces.get(i);
            if (!space.isEmpty()) {
                checksum += i * space.file.id;
            }
        }

        return checksum;
    }

    private List<Space> getAllSpaces(final List<SpaceBlock> spaceBlocks) {
        return spaceBlocks
                .stream()
                .map(SpaceBlock::getSpaces)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public record SpaceBlock(List<Space> spaces, int originalIndex) {
        public boolean isEmpty() {
            return spaces.getFirst().isEmpty();
        }

        public void moveBlockInThisBlock(final SpaceBlock fileBlock) {
            int firstFreeSpaceIndex = firstFreeSpaceIndex();
            for (int i = 0; i < fileBlock.spaces.size(); i++) {
                final Space file = fileBlock.spaces.get(i);
                spaces.remove(firstFreeSpaceIndex);
                spaces.add(firstFreeSpaceIndex, file);

                fileBlock.replaceSpaceAt(i, new Space(file.originalIndex));
                
                firstFreeSpaceIndex++;
            }
        }

        private void replaceSpaceAt(final int index, final Space space) {
            spaces.remove(index);
            spaces.add(index, space);
        }
        
        private int firstFreeSpaceIndex() {
            for (int i = 0; i < spaces.size(); i++) {
                if (spaces.get(i).isEmpty()) {
                    return i;
                }
            }
            
            return -1;
        }
        
        public int getAmountOfFreeSpace() {
            return (int) spaces.stream().filter(Space::isEmpty).count();
        }

        public List<Space> getSpaces() {
            return spaces;
        }

        public boolean fitsIn(final SpaceBlock otherBlock) {
            return otherBlock.getAmountOfFreeSpace() >= spaces.size();
        }
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

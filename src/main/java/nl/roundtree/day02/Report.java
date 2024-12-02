package nl.roundtree.day02;

import java.util.ArrayList;
import java.util.List;

public record Report(List<Level> levels) {
    
    public boolean isSafe() {
        return levelsAllDecreasingOrIncreasing(levels) && levelsIncreaseOrDecreaseWithinBounds(levels);
    }
    
    public boolean isSafeWithDampening() {
        if (!isSafe()) {
            final List<Level> levels = new ArrayList<>(this.levels);
            for (int i = 0; i < levels.size(); i++) {
                final Level removedLevel = levels.remove(i);
                
                if (levelsAllDecreasingOrIncreasing(levels) && levelsIncreaseOrDecreaseWithinBounds(levels)) {
                    return true;
                }
                
                levels.add(i, removedLevel);
            }
            
            return false;
        }
        
        return true;
    }
    
    private static boolean levelsAllDecreasingOrIncreasing(final List<Level> levels) {
        Level levelToCompare = levels.getFirst();
        SequenceMode sequenceMode = null;
        
        for (int i = 1; i < levels.size(); i++) {
            final Level currentLevel = levels.get(i);
            final SequenceMode currentSequenceMode = currentLevel.increasingOrDecreasing(levelToCompare);
            if (sequenceMode != null && currentSequenceMode != sequenceMode) {
                return false;
            }
            
            levelToCompare = currentLevel;
            sequenceMode = currentSequenceMode;
        }
        
        return true;
    }


    private static boolean levelsIncreaseOrDecreaseWithinBounds(final List<Level> levels) {
        Level levelToCompare = levels.getFirst();
        
        for (int i = 1; i < levels.size(); i++) {
            final Level level = levels.get(i);
            
            if (!level.isDifferenceWithinBounds(1, 3, levelToCompare)) {{
                return false;
            }}
            
            levelToCompare = level;
        }
        
        return true;
    }
}

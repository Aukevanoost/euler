package euler.puzzles.p5solution.strategy;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class BruteForceStrategy implements IStrategy{
    public int run(int min, int max) {
        List<Integer> dividers = findBiggestDividers(min, max);

        int output = 0;
        // Calculate biggest step he can make per check
        // smallest item dividable by a and b = a * b, so lets use that as step for the 2 biggest dividers
        int step = (dividers.size() >= 2) 
            ? dividers.get(dividers.size()-1) * dividers.get(dividers.size()-2)
            : max;
        

        boolean found = false;
        while(!found) {
            output += step;
            found = isDividableByAllDividers(dividers, output);
        }

        return output;
    }

    private boolean isDividableByAllDividers(List<Integer> dividers, int target) {
        return (dividers.stream().allMatch(divider -> target % divider == 0));        
    }

    private List<Integer> findBiggestDividers(int min, int max) {
        min = (min > 1) ? min : 2;
        max = (max > min) ? max : min+1;

        // Remove double dividers (keep only biggest dividers)
        // if something is dividable by 9, its also dividable by 3, so remove 3 to save time
        List<Integer> biggestDividers = new ArrayList<Integer>();
        for(int i = max; i >= min; i--) {
            if(!hasBiggerDivider(biggestDividers, i)){
                biggestDividers.add(i);
            } 
        }
        return biggestDividers;
    }

    private static boolean hasBiggerDivider(List<Integer> prevPrimes, int number) {
        return prevPrimes.stream().anyMatch(i -> i % number == 0);
    }
}
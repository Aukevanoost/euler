package euler.puzzles.p1solution.strategy;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.function.IntPredicate;

public class BruteForceStrategy implements IStrategy {
    public int run(int max) {
         
        return IntStream
            .range(1, max)
            .filter(this.checker)
            .sum();
    }

    private IntPredicate checker = (i) -> isDividableBy(i, 3) || isDividableBy(i, 5);

    private boolean isDividableBy(int i, int check) {
        return i % check == 0;
    } 
}
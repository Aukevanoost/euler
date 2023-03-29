package euler.puzzles.p6solution.strategy;

import java.util.stream.LongStream;

public class BruteForceStrategy implements IStrategy {
    public long run(int max) {
        long totalA = LongStream.rangeClosed(1, max).map((i)-> (long)Math.pow(i, 2)).sum();
        long totalB = (long)Math.pow(LongStream.rangeClosed(1, max).sum(), 2);
        return totalB - totalA;
    }
} 
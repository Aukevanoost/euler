package euler.puzzles.p8solution.strategy;

import java.util.stream.LongStream;
import java.util.stream.Collectors;

public class BruteForceStrategy implements IStrategy {
    public long run(String input, int chunkSize) {
        int iterations = input.length() - chunkSize;
        long biggestSum = LongStream
            .rangeClosed(0, iterations)
            .map(i -> LongStream.rangeClosed(i, i + chunkSize - 1).map(x -> this.makeInt(input, (int)x)).reduce((a,b) -> b * a).orElse(-1))
            .max().orElse(-1);

        return biggestSum;
    }



    // The ASCII table is arranged so that the value of the character '9' is nine greater than the value of '0'; 
    // the value of the character '8' is eight greater than the value of '0'; and so on.
    // [https://stackoverflow.com/questions/46343616/how-can-i-convert-a-char-to-int-in-java]
    private long makeInt(String input, int index) {
        return (long) (input.charAt(index) - '0');
    }
}
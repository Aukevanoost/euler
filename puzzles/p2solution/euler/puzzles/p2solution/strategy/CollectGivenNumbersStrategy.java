package euler.puzzles.p2solution.strategy;

import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class CollectGivenNumbersStrategy implements IStrategy {
    public int run(int max) {
        int[] list = findNext(max, max-1);
        //return IntStream.of(fibonacciSec).boxed().map(x -> x.toString()).collect(Collectors.joining(", "));
        System.out.println("[" + IntStream.of(list).filter(x -> x % 2 == 0 && x > 1).boxed().map(x -> x.toString()).collect(Collectors.joining(", ")) + "]");
        return IntStream.of(list).filter(x -> x % 2 == 0).sum();
    }

    public int[] findNext(int size, int i) {
        if(i <= 0) {
            int[] output = new int[size];
            output[0] = 1;
            return output;
        }
        int[] output = findNext(size, i-1);

        int nextNumber = (i == 1) ? output[i-1] * 2 : output[i-1] + output[i-2];
        output[i] = nextNumber;

        return output;
    }
}
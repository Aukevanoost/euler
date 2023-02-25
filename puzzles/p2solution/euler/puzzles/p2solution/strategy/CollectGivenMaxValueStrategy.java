package euler.puzzles.p2solution.strategy;

import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class CollectGivenMaxValueStrategy implements IStrategy {
    public static int SIZE_CHUNK = 20;
    public static int START_SIZE = 40;

    public int run(int max) {
        int[] list = findNext(new int[START_SIZE], 0, max);
        //System.out.println("[" + IntStream.of(list).filter(x -> x % 2 == 0 && x > 1).boxed().map(x -> x.toString()).collect(Collectors.joining(", ")) + "]");
        return IntStream.of(list).filter(x -> x % 2 == 0).sum();
    }

    public int[] findNext(int[] input, int i, int max) {
        if(i == 0) {
            input[0] = 1;
        }

        i += 1;
        int nextNumber = (i == 1) ? input[i-1] * 2 : input[i-1] + input[i-2];
        if(nextNumber > max) return input;

        if(i >= input.length) {
            input = expandArray(input);
        }
        input[i] = nextNumber;

        return findNext(input, i, max);
    }

    public int[] expandArray(int[] input) {
        final int newSize = input.length + SIZE_CHUNK;
        int[] newArr = new int[newSize];
        System.out.println("Adding: " + SIZE_CHUNK + ", total is now: " + newSize);

        for(int i = 0; i < input.length; i++) {
            newArr[i] = input[i];
        }

        return newArr;
    }
}
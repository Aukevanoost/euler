package euler.puzzles.p4solution.strategy;

import java.util.stream.IntStream;
import java.util.function.IntUnaryOperator;

public class ChunkBasedBruteForceStrategy implements IStrategy{
    private static int CHUNK_SIZE = 20;

    public int run(int digits) {
        double max = 9.0;
        double min = 0.0;
        if(digits > 1) {
            max = Math.pow(10, (double)digits) - 1;
            min =  Math.pow(10, (double)digits-1);
        }

        return findPalindrome((int) max, (int) min);
    }

    public int findPalindrome(int max, int min) {
        int chunks = getAmountOfChunksAvailable(max, min);

        IntUnaryOperator palindromeFinder = getPalindromeFinder(max, min);

        return IntStream
            .range(0, chunks)
            .map(i -> { 
                int chunkMax = max - (i * CHUNK_SIZE);
                int chunkMin = max - ((i+1) * CHUNK_SIZE);
                if(chunkMin < min) chunkMin = min;
                return processChunk(chunkMax, chunkMin, palindromeFinder);
            })
            .filter(i -> i >= 0)
            .findFirst().orElseGet(() -> -1);
    }



    private int processChunk(int chunkMax, int chunkMin, IntUnaryOperator palindromeFinder) {
        return IntStream.range(chunkMin, chunkMax).map(palindromeFinder).max().orElseGet(() -> -1);
    }

    private IntUnaryOperator getPalindromeFinder(int rangeMax, int rangeMin) {
        return (a) -> {
            int palindrome = -1;
            for(int b = rangeMax; b > rangeMin; b--) {
                int output = a * b;
                if(isPalindromic(output) && palindrome < output) {
                    palindrome = output;
                }
            }
            return palindrome;
        };
    }

    private boolean isPalindromic(int input) {
        var strA = Integer.toString(input);
        var strB = new StringBuilder(strA).reverse().toString();
        return strA.equals(strB);
    } 

    private int getAmountOfChunksAvailable(int max, int min) {
        return (int)Math.ceil( (max - min) / CHUNK_SIZE) ;
    }
}
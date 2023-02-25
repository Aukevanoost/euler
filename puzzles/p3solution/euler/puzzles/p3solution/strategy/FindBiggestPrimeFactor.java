package euler.puzzles.p3solution.strategy;

import java.util.List;
import java.util.ArrayList;

//
// OPTIMIZED TRIALDIVISION FOR EULER QUESTION 
// (see TrialDivision.java for explanation)
public class FindBiggestPrimeFactor implements IStrategy{
    public long run(long n) {
        return findBiggerPrime(n, 2);
    }

    private static int findBiggerPrime(long n, int prevPrime) {    
        int offset = prevPrime;
        while(n % offset != 0) offset++;

        int k = 0;
        while(n % offset == 0) {
            n = n / offset;
            k++;
        }

        if (n > 1) {
            offset = findBiggerPrime(n, offset);
        }

        return offset;
    }
}
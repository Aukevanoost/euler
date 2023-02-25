package euler.puzzles.p3solution.strategy;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
//
// Starting algorithm to find all unique prime-factors
// 1) Have input (n=15)
// 2) Find smallest natural number divisable by the given number (offset=3)
// 3) Find out the power of the first found natural number (k=1 [15 / 3 = 5]; n=5) )
// 4) add offset(prime) to unique prime list
  // 5) RECURSIVE, calculate trialDivison of rest (n=5)
//
// end with [3, 5] (3 x 5 = 15) 
public class TrialDivisionStrategy implements IStrategy{
    public long run(long number) {
        List<Integer> factors = trialDivision(number); 
        return factors.stream().max(Integer::compareTo).get();
    }

    private List<Integer> trialDivision(long n) {

        int offset = 2;
        while(n % offset != 0) offset++;


        int k = 0;
        while(n % offset == 0) {
            n = n / offset;
            k++;
        }


        var output = new ArrayList<Integer>(List.of(offset));

        if (n > 1) {
            output.addAll(trialDivision(n));
        }

        return output;
    }
}
package euler.puzzles.p10solution;

import euler.puzzles.p10solution.strategy.*;
import euler.contract.IPuzzle;

import java.util.function.Supplier;

//
// WORKS BUT NEED TO REFACTOR TO IMMUTABLE
//
public class Solution implements IPuzzle {
    public String explain() {
        return "Find the sum of all the primes below two million.";
    }

    public Supplier<String> solve() {
        return () -> {
            IStrategy strat = new SieveOfErastosthenesStrategy();
            return String.valueOf(strat.run(2_000_000 ));
        };
    }


    public int key() {
        return 10;
    }

    public static Solution get() { return new Solution(); }
}
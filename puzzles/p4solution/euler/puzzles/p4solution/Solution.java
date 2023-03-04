package euler.puzzles.p4solution;

import euler.contract.IPuzzle;
import euler.puzzles.p4solution.strategy.*;
import java.util.function.Supplier;

public class Solution implements IPuzzle {
    public String explain() {
        return "Find the largest palindrome made from the product of two 3-digit numbers.";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new ChunkBasedBruteForceStrategy();
            return String.valueOf(strat.run(3));
        };
        return solver;
    }

    
    public int key() {
        return 4;
    }
}
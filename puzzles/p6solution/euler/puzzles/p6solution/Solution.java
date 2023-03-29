package euler.puzzles.p6solution;

import euler.puzzles.p6solution.strategy.*;
import euler.contract.IPuzzle;
import java.util.function.Supplier;

public class Solution implements IPuzzle{
    public String explain() {
        return "Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new BruteForceStrategy();
            return String.valueOf(strat.run(100));
        };
        return solver;
    }

    
    public int key() {
        return 6;
    }
}
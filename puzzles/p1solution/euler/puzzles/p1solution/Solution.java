package euler.puzzles.p1solution;

import euler.contract.IPuzzle;
import euler.puzzles.p1solution.strategy.*;
import java.util.function.Supplier;

public class Solution implements IPuzzle {
    public String explain() {
        return "Find the sum of all the multiples of 3 or 5 below 1000.";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new BruteForceStrategy();
            return String.valueOf(strat.run(1000));
        };
        return solver;
    }

    public int key() {
        return 1;
    }
}
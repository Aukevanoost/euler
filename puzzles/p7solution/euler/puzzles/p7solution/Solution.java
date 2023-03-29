package euler.puzzles.p7solution;

import euler.puzzles.p7solution.strategy.*;
import euler.contract.IPuzzle;
import java.util.function.Supplier;

public class Solution implements IPuzzle{
    public String explain() {
        return "What is the 10 001st prime number?";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new BruteForceStrategy();
            return String.valueOf(strat.run(10_001));
        };
        return solver;
    }

    
    public int key() {
        return 7;
    }
}
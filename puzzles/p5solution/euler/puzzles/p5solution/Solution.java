package euler.puzzles.p5solution;

import euler.puzzles.p5solution.strategy.*;
import java.util.function.Supplier;
import euler.contract.IPuzzle;

public class Solution implements IPuzzle{
    public String explain() {
        return "What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new BruteForceStrategy();
            return String.valueOf(strat.run(1,20));
        };
        return solver;
    }

    
    public int key() {
        return 5;
    }
}
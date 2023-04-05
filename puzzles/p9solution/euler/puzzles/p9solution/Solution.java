package euler.puzzles.p9solution;

import euler.puzzles.p9solution.strategy.*;
import euler.contract.IPuzzle;
import java.util.function.Supplier;

public class Solution implements IPuzzle {
    public String explain() {
        return "There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new EuclidFormulaStrategy();
            return String.valueOf(strat.run(1000));
        };
        return solver;
    }

    
    public int key() {
        return 9;
    }
}
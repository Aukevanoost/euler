package euler.puzzles.p2solution;

import euler.contract.IPuzzle;
import euler.puzzles.p2solution.strategy.*;
import java.util.function.Supplier;

public class Solution implements IPuzzle {
    public String explain() {
        return "By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new CollectGivenMaxValueStrategy();
            return String.valueOf(strat.run(4000000));
        };
        return solver;
    }

    
    public int key() {
        return 2;
    }
}
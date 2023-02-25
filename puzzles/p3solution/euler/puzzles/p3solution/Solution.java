package euler.puzzles.p3solution;

import euler.contract.IPuzzle;
import euler.puzzles.p3solution.strategy.*;
import java.util.function.Supplier;

public class Solution implements IPuzzle {
    public String explain() {
        return "What is the largest prime factor of the number 600851475143 ?";
    }

    public Supplier<String> solve() {
        Supplier<String> solver = () -> {
            IStrategy strat = new FindBiggestPrimeFactor();
            return String.valueOf(strat.run(600851475143L));
        };
        return solver;
    }

    
    public int key() {
        return 3;
    }
}
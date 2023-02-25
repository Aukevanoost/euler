package euler.contract;

import java.util.function.Supplier;

public interface IPuzzle {
    // Explain the problem;
    String explain();

    // Index within the treemap
    int key();

    // Solve the puzzle
    Supplier<String> solve();
}
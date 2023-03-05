module euler.puzzles.p5solution {
    requires euler.contract;
    provides euler.contract.IPuzzle with euler.puzzles.p5solution.Solution;
}
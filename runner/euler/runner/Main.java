package euler.runner;

import java.io.Console;
import java.util.ServiceLoader;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import euler.contract.IPuzzle;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String... args) {
        Console console = System.console();

        if(console == null) {
            System.out.println("Console unavailable, exiting program"); //
            return;
        }

        console.writer().println("=====| WELCOME TO THE EULER SOLUTION RUNNER |=====");
        console.writer().println("finding solutions..");
        Map<Integer, IPuzzle> solutions = Main.findSolutions();
        if(solutions.size() < 1) {
            console.writer().println("No solutions found, exiting program.");
            return;
        }

        console.format("%d solutions found. [-1=quit]", solutions.size());
        console.writer().println();

        String input = "";
        RUNNER: while(input != null && !input.equals("-1")) {
            try {
                input = console.readLine("Which problem do you want to solve? (number):");

                Integer problemId = Integer.valueOf(input);
                if(problemId < 0) break RUNNER;

                console.writer().println("Checking... ");
                if(!solutions.containsKey(problemId)) {
                    console.writer().println("Euler solution does not exist!");
                    throw new IllegalArgumentException();
                }

                IPuzzle puzzle =  solutions.get(problemId);
                console.writer().println("-------------------------------------------------------------------------------------");
                console.writer().format("Problem %d: %s \n", problemId, puzzle.explain() );
                Main.runEulerProblem(console, puzzle.solve());

            } catch (NumberFormatException nfe) {
                console.writer().println("Input has to be numeric");
            } catch (Exception e) {
                console.writer().println("Something went wrong, please try again.");
                e.printStackTrace(console.writer());
            }
        }

        console.writer().println("exiting program.");
    }

    public static void runEulerProblem(Console console, Supplier<String> answer) {
        var pre = Instant.now();
        String output = answer.get();
        var duration = Duration.between(pre, Instant.now());

        console.writer().format("output: %s, duration: %dNs \n",  output, duration.toMillis());
    }

    public static Map<Integer, IPuzzle> findSolutions() {
        Map<Integer, IPuzzle> solutions = new TreeMap<>();
        ServiceLoader<IPuzzle> loader = ServiceLoader.load(IPuzzle.class);
        for(IPuzzle puzzle : loader) {
            solutions.put(puzzle.key(), puzzle);
        }
        return solutions;
    }
}


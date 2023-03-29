package euler.puzzles.p7solution.strategy;

import java.util.stream.IntStream;
import java.util.LinkedList;

public class BruteForceStrategy implements IStrategy {
    public long run(int nth) {
        LinkedList<Integer> primes = IntStream.range(0,nth).boxed().reduce(new LinkedList<Integer>(), BruteForceStrategy::findNextPrime, (a, b) -> a);
        return primes.peekLast();
    }

    private static LinkedList<Integer> findNextPrime(LinkedList<Integer> a, Integer b) {
        // get biggest found previous prime number (starting by 2)
        b = (a.size() < 1) ? 2 : a.peekLast(); 

        Boolean isPrime = false;
        while(!isPrime) {
            final Integer check = b;
            // Check if dividable by previous primes
            isPrime = !(a.stream().anyMatch(n -> check % n == 0));
            // Increment if not a prime
            if(!isPrime) b++;
        }
        a.addLast(b);
        return a;
    }
}
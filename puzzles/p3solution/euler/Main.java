package  primes;

import java.time.Instant;
import java.time.Duration;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        System.out.println("================= STARTING =================");

        var pre = Instant.now();
        // ---------------------------------------------
        //List<Integer> factors = Main.trialDivision(600851475143L);
        //var output = factors.stream().boxed().max();
        int biggestFactor = Main.findBiggerPrime(600851475143L);
        var output = String.valueOf(biggestFactor);
        // ---------------------------------------------
        var duration = Duration.between(pre, Instant.now());

        System.out.println(String.format("output: %s", output));
        System.out.println(String.format("Duration: %dms", duration.toMillis()));
    }

    private static int findBiggerPrime(long n) {
        return Main.findBiggerPrime(n, 2);
    }

    private static int findBiggerPrime(long n, int prevPrime) {
        
        int offset = prevPrime;
        while(n % offset != 0) offset++;

        int k = 0;
        while(n % offset == 0) {
            n = n / offset;
            k++;
        }

        if (n > 1) {
            offset = Main.findBiggerPrime(n, offset);
        }

        return offset;
    }

    private static List<Integer> trialDivision(long n) {

        int offset = 2;
        while(n % offset != 0) offset++;


        int k = 0;
        while(n % offset == 0) {
            n = n / offset;
            k++;
        }


        var output = new ArrayList<Integer>(List.of(offset));

        if (n > 1) {
            output.addAll(Main.trialDivision(n));
        }

        return output;
    }
}


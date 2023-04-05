package euler.puzzles.p9solution.strategy;

import java.util.stream.LongStream;
import java.util.stream.Collectors;


/*
 *
 *  
 * The triple generated by Euclid's formula is primitive if and only if m and n are coprime and one of them is even.
 * 
 * Formula: a = m^{2} - n^{2}, b = 2m x n, c = m^{2} + n^{2} WHERE m > n > 0
 * [wiki: https://en.wikipedia.org/wiki/Pythagorean_triple]
*/
public class EuclidFormulaStrategy implements IStrategy {
    public int run(int sumOfTriplet) {
        Triplet temp = null;
        boolean keepLookingForTriplet;

        int n = 1;
        int m = 2;

        // Step one, find first triplet with N=1 and with the sum just above 1000
        //System.out.println("===| Step one");
        keepLookingForTriplet = true;
        while(keepLookingForTriplet) {
            temp = this.calcTriplet(m, n);
            if(temp.sum() >= 1000) keepLookingForTriplet = false;
            //System.out.println(String.format("(%03d, %03d) | %d + %d + %d = %d", m, n, temp.a(), temp.b(), temp.c(), temp.sum()));
            m++;
        }

        // Step 2, Stay as close to sum of 1000 as possible, find the right triplet
        // Above 1000 = m--, Below 1000 = n++
        //System.out.println("===| Step two");
        keepLookingForTriplet = true;
        while(keepLookingForTriplet) {
            if(m < n) return -1; // Failed to find the triplet
            if(temp.sum() > 1000) m--;
            if(temp.sum() < 1000) n++;

            temp = this.calcTriplet(m, n);
            if(temp.sum() == 1000) keepLookingForTriplet = false;
            //System.out.println(String.format("(%03d, %03d) | %d + %d + %d = %d", m, n, temp.a(), temp.b(), temp.c(), temp.sum()));
        }

        return temp.product();
    }

    private Triplet calcTriplet (int m, int n) {
        int a = (m*m) - (n*n);
        int b = (2 * m) * n;
        int c = (m*m) + (n*n);
        //System.out.println(String.format("%.1f + %.1f = %.1f",a,b,c));
        return new Triplet(a, b, c);
    }
}

record Triplet(int a, int b, int c) {
    public int sum() {
        return this.a + this.b + this.c;
    }
     public int product() {
        return this.a * this.b * this.c;
    }   
}
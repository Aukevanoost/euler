package euler.puzzles.p4solution.strategy;


public class BruteForceStrategy implements IStrategy{
    public int run(int digits) {
        double max = 9.0;
        double min = 0.0;
        if(digits > 1) {
            max = Math.pow(10, (double)digits) - 1;
            min =  Math.pow(10, (double)digits-1) - 1;
        }

        return iterateThroughRange((int) max, (int) min);
    }

    public int iterateThroughRange(int max, int min) {
        boolean isATurn = false;
        int palindrome = -1;
        for(int a = max; a > min; a--) {
            for(int b = max; b > min; b--) {
                int output = a * b;
                if(isPalinDromic(output) && palindrome < output) {
                    //System.out.println(String.format("> %03d x %03d = %d", a, b, a * b));
                    palindrome = output;
                }
            }
        }
        return palindrome;
    }

    private boolean isPalinDromic(int input) {
        var strA = Integer.toString(input);
        var strB = new StringBuilder(strA).reverse().toString();
        return strA.equals(strB);
    }

    
}
package euler.puzzles.p10solution.strategy;

import java.util.Arrays;
import java.util.stream.Stream;

// When the algorithm terminates, all the numbers in the list that are not marked are prime.
// [src: https://www.geeksforgeeks.org/sieve-of-eratosthenes/]
public class SieveOfErastosthenesStrategy implements IStrategy {

    public long run(final Integer size) {
        Sieve input = Sieve.of(size);
        return Stream.iterate(
                SieveIterator.start(input),
                SieveIterator::hasNext,
                SieveIterator::iterate
            )
            .map(SieveIterator::cleanQueue)
            .mapToLong(SieveIterator::getCurrentPrime)
            .sum();
    }
}

class SieveIterator {
    private SieveState _sieveState;

    private SieveIterator(SieveState state) {
        this._sieveState = state;
    }

    public SieveIterator iterate() {
        this._sieveState = _sieveState.getNext();
        return this;
    }

    public boolean hasNext() {
        return this._sieveState != null;
    }
    public SieveIterator cleanQueue() {
        this._sieveState = this._sieveState.disableAllCompositeSlots();
        return this;
    }
    public int getCurrentPrime() {
        return this._sieveState.prime();
    }

    public static SieveIterator start(Sieve sieve) {
        return new SieveIterator(new SieveState(sieve, 2));
    }
}

record SieveState(Sieve sieve, int prime) {
    public SieveState getNext() {
        return Stream
            .iterate(this.prime()+1, slot -> slot < sieve.size(), slot -> slot+1)
            .filter(sieve::isPrime)
            .findFirst()
            .map(prime -> SieveState.of(this.sieve, prime))
            .orElseGet(() -> null);
    }

    public SieveState disableAllCompositeSlots() {
        return new SieveState(this.sieve().updateSlots(this.prime()), this.prime());
    }

    private static SieveState of(Sieve newSieve, int prime) {
        return new SieveState(newSieve, prime);
    }

}

class Sieve {
    private final Boolean[] primeSlots;
    private Sieve(Boolean[] primeSlots) {
        this.primeSlots = primeSlots;
    }

    public Sieve updateSlots(int prime) {
        int getFirstCompositeSlotAfterPrime = slotIndexOf(prime) + prime;
        return new Sieve(
            Stream.iterate(getFirstCompositeSlotAfterPrime, i -> i < primeSlots.length, i -> i += prime).reduce(
                primeSlots,
                (slots, i) -> { slots[i] = false; return slots; },
                (a, b) -> null
            )
        );

    }

    public int size() {
        return primeSlots.length;
    }

    public boolean isPrime(int prime) {
        return this.primeSlots[slotIndexOf(prime)];
    }

    private int slotIndexOf(int prime) {
        return prime - 1;
    }

    public static Sieve of(int size) {
        var init = new Boolean[size];
        Arrays.fill(init, true);

        return new Sieve(init);
    }
}

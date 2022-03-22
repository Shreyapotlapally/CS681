package edu.umb.cs681.hw5;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();

    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
            this.from = from;
            this.to = to;
        }else{
            throw new RuntimeException("Exception: from=" + from + " to=" + to);
        }
    }

    public LinkedList<Long> getPrimes(){ return primes; };

    protected boolean isEven(long n){
        if(n%2 == 0){ return true; }
        else{ return false; }
    }

    protected boolean isPrime(long n){
        // 1 or lower numbers are not prime.
        if(n <= 1){ return false; }
        // Even numbers are not prime, except for 2.
        if( n > 2 && isEven(n) ){ return false; }
        long i;
        // Find a number "i" that can divide "n"
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        // If such a number "i" is found, n is not prime. Otherwise, n is prime.
        if (i == 1){ return true; }
        else{ return false; }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            if( isPrime(n) ){ primes.add(n); }
        }
    }

    public static void main(String[] args) {
        // Single-threaded prime number generation (with generatePrimes())
        PrimeGenerator objPrimeGenerator = new PrimeGenerator(1, 100);
        objPrimeGenerator.generatePrimes();
        objPrimeGenerator.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + objPrimeGenerator.getPrimes().size() + " Prime numbers are here.");

        // Single-threaded prime number generation (without using generatePrimes())
        PrimeGenerator objPrimeGenerator2 = new PrimeGenerator(1, 100);
        List<Long> primes = LongStream.rangeClosed(objPrimeGenerator2.from, objPrimeGenerator2.to)
                .filter( (long n)->objPrimeGenerator2.isPrime(n) )
                .boxed()
                .collect(Collectors.toList());
        primes.forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + primes.size() + " prime numbers are here.");

        // Single-threaded prime number generation (without using generatePrimes())
        PrimeGenerator objPrimeGenerator3 = new PrimeGenerator(1, 100);
        long size = LongStream.rangeClosed(objPrimeGenerator3.from, objPrimeGenerator3.to)
                .filter( (long n)->objPrimeGenerator3.isPrime(n) )
                .reduce( 0L, (long count, long n)->{
                    System.out.print(n + ", ");
                    return ++count;} );
        System.out.println("\n" + size + " prime numbers are here.");

    }
}
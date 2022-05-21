package edu.umb.cs681.hw18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
		getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new RunnablePrimeGenerator(1, 100));
        executor.shutdown();
	}

}
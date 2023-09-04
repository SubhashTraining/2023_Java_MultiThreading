package MichaelPogrebinsky.Join;



/*
 * 
 * 
In this exercise, we will efficiently calculate the following result = base1 ^ power1 + base2 ^ power2

Where a^b means: a raised to the power of b.

For example 10^2 = 100

We know that raising a number to a power is a complex computation, so we like to execute:

result1 = x1 ^ y1

result2 = x2 ^ y2

In parallel.

and combine the result in the end : result = result1 + result2

This way, we can speed up the entire calculation.
 * 
 */
 
 
import java.math.BigInteger;
import java.util.*;
public class Join_Exercise {
	
	
	public static void main(String args[]) {
		
		List<calPowerThread> threads = new ArrayList<>();
		
		threads.add( new calPowerThread(new BigInteger("2"), new BigInteger("0")));
		threads.add( new calPowerThread(new BigInteger("2"), new BigInteger("0"))) ;
		
		for(Thread t: threads)
			t.start();
		
		
		for(Thread t: threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		BigInteger sum = threads.get(0).getResult().add(threads.get(1).getResult());
		System.out.println(sum);
	}
	
	
	private static class calPowerThread extends Thread{
		
		
		private BigInteger base;
		private BigInteger power;
		private BigInteger result;
		private boolean isFinished ;
		
		public calPowerThread(BigInteger base, BigInteger power) {
			this.base=base;
			this.power= power;
			this.result=BigInteger.ONE;
			this.isFinished= false;
			
		}
		
		@Override
		public void run() {
			this.result = baseToPower();
			this.isFinished= true;
			
		}
		
		private BigInteger baseToPower() {			
			BigInteger result = BigInteger.ONE;
			if(power.compareTo(BigInteger.ZERO)==0)
				return result;
			
			if(base.compareTo(BigInteger.ZERO)==0)
				return BigInteger.ZERO;

			for(BigInteger i=BigInteger.ONE;i.compareTo(power)!=0;i=i.add(BigInteger.ONE)) {
				result = result.multiply(base);
			}
			return result;
		}

		public BigInteger getResult() {
			return result;
		}

		public boolean isFinished() {
			return isFinished;
		}
		
		
	}
}

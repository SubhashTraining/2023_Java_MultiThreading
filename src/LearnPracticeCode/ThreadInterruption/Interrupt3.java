package LearnPracticeCode.ThreadInterruption;

import java.math.BigInteger;

/*
 
 * Number two is if the thread we're trying to interrupt is handling the
 * Interrupt signal explicitly, let's explore those scenarios and practice.
 * 
 * @author subhperu
 *
 */


//  We will now add a condition and explicitly inject an inteerupt code here
public class Interrupt3 {
	
	public static void main (String args[]) {
		
		
		// check the argument new BigInteger("10024542")
		Thread t = new Thread(new MyRunnable(new BigInteger("1212454"), new BigInteger("201111111")));
		t.start();
		// this interrupt with now have an effect as our thread below 
		//frequently checks for interrupt signals
		t.interrupt();
		
	}
			
	
	private static class MyRunnable implements Runnable{
		//java.math.BigInteger
		BigInteger base;
		BigInteger power;		
		
		
		public MyRunnable(BigInteger base, BigInteger power) {
			this.base = base;
			this.power =power;
		
			
		}
		
		

		//anything to power 0 is 1
		@Override
		public void run() {
		
			System.out.println(base +"^" +power + "=" +pow() );
			
		}
		
		
		public BigInteger pow() {
			BigInteger result = BigInteger.ONE;
			for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0 ; i= i.add(BigInteger.ONE)){
				
				//We will check for any interrupt signal 
				if(Thread.currentThread().isInterrupted()){
					System.out.println("Premature Interruption - Thread ended");
					return BigInteger.ZERO;
				}
				result = result.multiply(base);
			}
			
			return result;
		}
		
	}
	
}

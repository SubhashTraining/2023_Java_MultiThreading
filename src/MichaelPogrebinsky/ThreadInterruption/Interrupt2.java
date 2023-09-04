package MichaelPogrebinsky.ThreadInterruption;

import java.math.BigInteger;

/*
 
 * Number two is if the thread we're trying to interrupt is handling the
 * Interrupt signal explicitly, let's explore those scenarios and practice.
 * 
 * @author subhperu
 *
 */


// The below program takes a long time to execute , we will see how to interrupt this in
// next eg
public class Interrupt2 {
	
	public static void main (String args[]) {
		
		
		// check the argument new BigInteger("10024542")
		Thread t = new Thread(new MyRunnable(new BigInteger("12124545122"), new BigInteger("5454545454545")));
		t.start();
		
		
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
				result = result.multiply(base);
			}
			
			return result;
		}
		
	}
	
}

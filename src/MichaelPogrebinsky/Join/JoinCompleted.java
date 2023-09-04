package MichaelPogrebinsky.Join;

import java.util.Arrays;
import java.util.*;
import java.math.*;

public class JoinCompleted {

	public static void main (String args[]) {
		
		
		List<Long> inputList = Arrays.asList(154L,45454L,45L,0L,4L,2454L,2L);
		System.out.println(inputList);
		List<Thread> threads = new ArrayList<>();
		
		for(Long input: inputList) {			
			threads.add(new FactorialThread(input));			
		}
		
		for(Thread t: threads)
		{
			t.start();
		}
		
		//we will add a join method specifying the wait time to pause the main thread to wait
		// for the factorial threads to complete
		for(Thread t: threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<inputList.size();i++) {
			FactorialThread thread = (FactorialThread) threads.get(i);
			if(thread.isFinished) {
				System.out.println("The factorial of input "+inputList.get(i)+" equals to: "+ thread.getFactorial());
			}
			else {
				System.out.println("The thread calculing factorial for input "+inputList.get(i)+" is still in progress");
				System.out.println();
			}
		}
		
		
	}
	
	
	private static class FactorialThread extends Thread{
		
		private Long input ;
		private BigInteger factorial;
		private boolean isFinished;
		
		public FactorialThread(Long input) {
			this.input= input;
			factorial = BigInteger.ZERO;
			isFinished=false;
		}
		
		@Override
		public void run() {
			this.factorial = factorial();
			this.isFinished= true;
		}
		
		private BigInteger factorial() {
			BigInteger result = BigInteger.ONE;
			if(input==0)
				return BigInteger.ZERO;
			for(Long i= input; i>0;i--) {
				result = result.multiply(BigInteger.valueOf(i));
			}
			
			return result;
		}

		

		public BigInteger getFactorial() {
			return factorial;
		}

		public boolean isFinished() {
			return isFinished;
		}
		
		
		
		
		
	}
	
	
}

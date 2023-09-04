package MichaelPogrebinsky.Join;

/*
 *	What if a thread has started and then it has to wait for another thread to be completed
 *	proceed further. One way is that you can stop and have a loop checking if thread X is completed 
 *	or not. This is a bad approach
 *
 * What if you can put this thread to sleep and wait for thread X to complete.
 * and resume later
 *
 * This can be achieved using join
 * Thread.join()
 * 
 *  public final void join()
 *  public final void join(long millsec, int nanosecs)
 *  public final void join(long millisecs)
 *  
 *  More control over independent threads
 *  Safely collect and aggregate results
 *  Gracefully handle runaway threads
 *  
 *  
 *  WITH THREADS
 *  - do not rely on order of execution
 *  - always use thread coordination
 *  - design code for worst case scneario
 *  - Threads may take unreasonably longer time
 *  - always use thread.join with a time limit
 *  - stop the thread if its not dont in time
 *  
 * @author subhperu
 *
 */

import java.util.*;
import java.math.BigInteger;

public class Join {

	
	public static void main(String args[]) {
		
		//Arrays.asList returns a List not an arrayList, so you cant assign this to an ArrayList<Long>
		List<Long> inputNumbers = Arrays.asList(0L,2253L,93989L,24L,545L,98898L); 
		
		List<Thread> threadList = new ArrayList<>();
		
		for(Long input : inputNumbers) {			
			threadList.add(new 	FactorialThread(input));			
		}
		
		for(Thread t: threadList)
			t.start();
		
		
		
		for(int i=0;i<=inputNumbers.size()-1;i++) {
			
			FactorialThread thread = (FactorialThread) threadList.get(i);
			if(thread.isFinished) {
				System.out.println("The factorial of input "+inputNumbers.get(i)+" equals to: "+ thread.getFactorial());
			}
			else {
				System.out.println("The thread calculing factorial for input "+inputNumbers.get(i)+" is still in progress");
			}
			
		}
		
		// This program executes for a long time. and the main thread will still 
		// mark most of the thread as in progress
		// if you want main to wait until all the user threads are completed create a join for those threads 
		// so your main thread will sleep until the join is completed or the time limit is met
		// Check next class file 
		
		
	}
	
	
	private static class FactorialThread extends Thread
	{
		
		Long input;
		Boolean isFinished;
		BigInteger  factorial ;
		
		
		public FactorialThread(Long input) {
			this.input= input;
			isFinished=false;
			factorial = BigInteger.ONE;
		}
		
		
		
		@Override
		public void run() {			
			factorial = findFactorial();
			isFinished= true;		
		}
		
		private BigInteger findFactorial() {
			
			BigInteger result = BigInteger.ONE;
			for(Long i=input; i>0;i--) {
				factorial = factorial.multiply(BigInteger.valueOf(i));
				//factorial.multiply(new BigInteger(Long.toStrin(i));
			}
			return result;
		}
		
		
		
		public Boolean getIsFinished() {
			return isFinished;
		}
		public BigInteger getFactorial() {
			return factorial;
		}
		
		
		
	}
	
	
}






























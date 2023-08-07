package LearnPracticeCode.ThreadInterruption;

import java.util.concurrent.TimeUnit;

/*
 * The first one, if the thread we're trying to interrupt is currently executing
 * a method which throws an interrupted exception when it gets interrupted.
 * 
 * 
 * 
 * @author subhperu
 *
 */
public class Interrupt1 {
	
	public static void main (String args[]) {
		
		Thread t = new Thread(new MyRunnable());
		t.start();
		//To stop the blocking thread t which waits for 50000 ms 
		t.interrupt();
		
		
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {}
		
		
		System.out.println("Resuming main Thread");
		// this throws an interrupted exception and the application ends
		
		
	}
			
	
	private static class MyRunnable implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Thread interrupted");
			}
			
		}
		
	}
	
}

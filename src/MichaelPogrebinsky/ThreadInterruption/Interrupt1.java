package MichaelPogrebinsky.ThreadInterruption;

import java.util.concurrent.TimeUnit;

/*
 * The first one, if the thread we're trying to interrupt is currently executing
 * a method which throws an interrupted exception when it gets interrupted.
 * 
 * That is not correct. The thread.interrupt() is just a way to signal another thread that it needs to stop. 
 * It is up to us to stop it, if possible.
 * 
 * you need to explicitly throw an exception to stop the thread from execution
 * We need to add a return statement inside the catch interrupted block to stop the thread
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Is the MyRunnable thread still alive:"+t.isAlive() );
		
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
					e.printStackTrace();
				}
			
			System.out.println("Am i going to printed");
		}
		
	}
	
}

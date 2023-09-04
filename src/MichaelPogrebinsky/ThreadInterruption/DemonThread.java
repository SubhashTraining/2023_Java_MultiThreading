package MichaelPogrebinsky.ThreadInterruption;

import java.math.BigInteger;
import java.io.*;

/*
 * Demon Threads are background tasks that should not block our application from terminating
 * Code in worker thread is not in our control and we do not want it to block application from terminating 
 * 
 * It provides services to user threads for background supporting tasks. 
 * It has no role in life than to serve user threads.
 * he sole purpose of the daemon thread is that it provides services to user thread for background supporting task. If there is no user thread, why should JVM keep running this thread. 
 * That is why JVM terminates the daemon thread if there is no user thread.
 * 
 * I tried to complete the main thread but it was not printing or writing to a file
 * we need to keep any thread running to perform this. But if the main thread or all the user threads(any other threads non demon) are done
 * the demon thread is also killed 
 */
public class DemonThread {

	public static void main (String args[]) {
		
		
		
		Thread t = new Thread(new MyRunnable());
		//We will mark this thread as Demon Thread
		t.setDaemon(true);
		t.start();
		
		try {
			
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	
	private static class MyRunnable implements Runnable{
		//java.math.BigInteger
		BigInteger base;
		BigInteger power;
		
				
		public MyRunnable() {
			this.base = new BigInteger("5");
			this.power =new BigInteger("200");		
			
		}
		//anything to power 0 is 1
		@Override
		public void run() {
		
			
			try {
				try {
					
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileOutputStream fos = new FileOutputStream("Demon.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				byte[] byteArray = pow().toByteArray();
				oos.write(byteArray);
				oos.close();
				fos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catchint block
				e.printStackTrace();
			}
			
			
		}	
		
		public BigInteger pow() {
			BigInteger result = BigInteger.ONE;
			for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0 ; i= i.add(BigInteger.ONE)){
				result = result.multiply(base);
			}
			System.out.println(result);
			return result;
		}
		
	}
	
	
	
}

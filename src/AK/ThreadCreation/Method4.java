package AK.ThreadCreation;

import java.util.concurrent.TimeUnit;

/*
 * 		This method is same as Previous one, but passess the runnable to Thread class
 */

public class Method4 {

	
	
	public static void main(String args[]) {
		
		new Thread(new MyRunnable()).start();
		Thread thread2= new Thread(new MyRunnable());
		thread2.start();
	}
	
	
	private static class MyRunnable implements Runnable{
		
		private int ID;
		private static int counter=0;
		public MyRunnable() {
			this.ID=++counter;		
		}		
		
		@Override
		public void run() {
			
			for(int i=1;i<=100;i++) {
				System.out.println("Thread"+ID +" Task:"+i);
			}
			
		}
		
		
	}
	
}

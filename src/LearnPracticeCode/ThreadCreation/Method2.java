package LearnPracticeCode.ThreadCreation;

import java.util.concurrent.TimeUnit;

/*
 * 		This method does not create a thread reference and once the thread starts you cant access the 
 * 		reference to control it
 * 		This type of thread invocation is used when you want  to run a process that is independent of the other parts
 * 		of the application and perform standalone operations
 * 
 * 		you cant access this.getName or any  of the thread class Methods
 * 
 * 		Also you will not have the start method
 */

public class Method2 {

	
	
	public static void main(String args[]) {
		
		new MyRunnable();
		new MyRunnable();
	
	}
	
	
	private static class MyRunnable implements Runnable{
		
		private int ID;
		private static int counter=0;
		public MyRunnable() {
			this.ID=++counter;
			new Thread(this).start();
			
		}
		
		
		
		@Override
		public void run() {
			
			for(int i=1;i<=100;i++) {
				System.out.println("Thread"+ID +" Task:"+i);
			}
			
		}
		
		
	}
	
}

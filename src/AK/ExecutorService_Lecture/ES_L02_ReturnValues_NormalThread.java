package AK.ExecutorService_Lecture;

import java.util.concurrent.TimeUnit;

// Return results using wait and notify

public class ES_L02_ReturnValues_NormalThread {
	
	public static void main(String args[]) {
		
		
		
		task task1 = new task();
		Thread t = new Thread(task1);
		
		t.start();
		
		
		System.out.println(task1.getResults());
				
		
	}
	

	
	private static class task implements Runnable{
		
		private int result=1;
		private boolean isDone = false;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for(int i=1;i<=5;i++) {
				
				result= result*i;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			isDone =true;
			synchronized(this)
			{
				this.notify();
			}
			
		}
		
		
		public int getResults() {
			
			if(!isDone) {
				synchronized(this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return result;
			
		}
		
		
	}
	
}

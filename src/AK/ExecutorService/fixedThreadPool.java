package AK.ExecutorService;

/*
 *  Thread pools, reuses the thread being created so there are time saved in creation of new thread and consolidating and maintaing them
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class fixedThreadPool {
	
	public static void main(String agrs[]) {
		
		ExecutorService parallelExecutor = Executors.newFixedThreadPool(3);
		for(int i=1;i<=3;i++) {
			
			parallelExecutor.execute(new MyThreads(i));
			
		}
		parallelExecutor.shutdown();	
	}
	
	
	/*
	 * Pool 1- Thread 1 -> is the name of the thread being created by Executor Service
	 * 
	 
	 */
	
	
	private static class MyThreads implements Runnable{
	
		private int taskId;
		
		public MyThreads(int taskID) {
			this.taskId= taskID;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for(int i=0;i<=5;i++) {
				
				System.out.println("Task ID: " +taskId+" SubProcess: "+i+" is in progress ");
				try {
					TimeUnit.MILLISECONDS.sleep((long) (Math.random()*1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
			System.out.println(String.format(Thread.currentThread().getName()+" has completed execution", null));
			
		}
		
		
		
	}

}


/*
 * A specical case of FixedThreadPool but the pool size as 1
 * The tasks are executed 1 by 1 no matter how many tasks is being submitted
 * the need for synchronization or serialization can be achieved by doing so. 
 *  
 */


package LearnPracticeCode_ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class SingleThreadExecutor {

	public static void main(String agrs[]) {
		
		ExecutorService parallelExecutor = Executors.newSingleThreadExecutor();
		for(int i=1;i<=3;i++) {
			
			parallelExecutor.execute(new MyThreads(i));
			
		}
		parallelExecutor.shutdown();	
	}
	
	
	
	
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
			System.out.println(taskId +" is completed");
			
		}
		
		
		
	}

}

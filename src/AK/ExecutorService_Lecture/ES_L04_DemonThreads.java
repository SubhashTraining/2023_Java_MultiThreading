package AK.ExecutorService_Lecture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ES_L04_DemonThreads {

	
	
	public static void main(String args[]) {
		
		
		ExecutorService parallelExecutor = Executors.newFixedThreadPool(5, new setDemonThread());
		
		// AS soon as Thread 1 and Thread 3 are completed Thread 2 and 4 are auto completed
		
		parallelExecutor.submit(new myCallable(1000));
		parallelExecutor.submit(new myCallable(10000));
		parallelExecutor.submit(new myCallable(1000));
		parallelExecutor.submit(new myCallable(20000));
		
		
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("***********************");
		//Runnable
		parallelExecutor.execute(new myRunnable(1000));
		parallelExecutor.execute(new myRunnable(10000));
		parallelExecutor.execute(new myRunnable(1000));
		parallelExecutor.execute(new myRunnable(20000));
		
		
		
		parallelExecutor.shutdown();
	}
	
	
	
	
	private static class myRunnable implements Runnable{

		
		long sleepTme;
		
		public myRunnable(long sleepTme) {
			
			this.sleepTme=sleepTme;
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
		
			
			boolean isDaemon = Thread.currentThread().isDaemon();
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTme);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(String.format(Thread.currentThread().getName()+" has completed") );
			
			
			
		}
		
		
		
		
	}
	
	
	
	private static class myCallable implements Callable<Integer>{

		
		long sleepTme;
		
		public myCallable(long sleepTme) {
			
			this.sleepTme=sleepTme;
			
		}
		
		@Override
		public Integer call() throws Exception {
			// TODO Auto-generated method stub
		
			
			boolean isDaemon = Thread.currentThread().isDaemon();
			for(int i=1;i<=5;i++) {
				System.out.println(String.format("Completed Task:%d of thread %s (Demon:%s) ",i, Thread.currentThread().getName(),
						Thread.currentThread().isDaemon()));
				TimeUnit.MILLISECONDS.sleep(sleepTme);
			}
			
			System.out.println(String.format(Thread.currentThread().getName()+" has completed") );
			
			
			return null;
		}
		
		
		
		
	}
	
	
	private static class setDemonThread extends NamedThreadFactory{
		
		private int count =0;
		
		@Override
		public Thread newThread(Runnable r) {
			count++;
			Thread Thread= super.newThread(r);
			if(count %2==0)
			{
				Thread.setDaemon(true);
			}
			
			return Thread;
		}
		
		
	}
	
	private static class NamedThreadFactory implements ThreadFactory{
		
		
		private int count =0;
		private String prefix = "ExecutorThread";

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			
			//return new Thread(runnable, name)
			return new Thread(r,prefix+ ++count);
		}
		
		
		
		
	}
}

package AK.ExecutorService_Lecture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/*
  The name of the thread can be set using two ways
  		Thread.currentThread.setName()
  		Thread.currentThread.getName()
  		Thread t = new Thread(new Runnable())'
  		t.setName();
 */

public class ES_L01_RenameThreads {
	
	public static void main(String agrs[]) throws InterruptedException {
		
		Thread t1 = new Thread(new loop());
		t1.setName("Thread1");
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ExecutorService parrallelExecutor = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		parrallelExecutor.execute(new loop());
		parrallelExecutor.execute(new loop());
		
		
		// Lets see if the threads are getting reused in the Cached Thread pool
			// Create few threads and sleep 
			// create new threads again using Executors
		for(int i=0;i<=3;i++) {
			parrallelExecutor.execute(new loop());
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		for(int i=0;i<=3;i++) {
			parrallelExecutor.execute(new loop());
		}
		
		parrallelExecutor.shutdown();
	}

}

// we need create a instance of ThreadFactory interface and pass that to the exector Servcie
class NamedThreadFactory implements ThreadFactory{

	private static int count =0;
	private static final String prefix ="WorkerThread-";
	
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		
		
		return new Thread(r, prefix+ ++count);
	}
	
	
}


 class loop implements Runnable{

	private static int count=0;
	private int currentThreadInstance;
	private String task;
	
	public loop()
	{
		
		this.currentThreadInstance= ++this.count;
		this.task= "Thread"+this.currentThreadInstance;
	}
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		System.out.println(String.format("Thread %d has started Execution", this.currentThreadInstance));
		
		for(int i=1;i<=5;i++) {
			
			System.out.println(String.format("Executing task %d of thread %d", i,this.currentThreadInstance));
		}
		
		System.out.println(String.format(Thread.currentThread().getName()+ " has completed execution"));
		
	}
	
	
	
}

package MichaelPogrebinsky.Basics;



import java.util.List;
import java.util.ArrayList;

class MultiThreadExecutor{
	
	List<Thread > list_Thread  = new ArrayList<Thread>();
	
	public MultiThreadExecutor(List<Runnable> runnables){
		
		for(Runnable runnable: runnables) {
			list_Thread.add(new Thread(runnable));
		}
		
		
	}
	
	public void excuteThreads() {
		
		for(Thread  thread : list_Thread) {
			thread.start();
		}
		
	}
	
}



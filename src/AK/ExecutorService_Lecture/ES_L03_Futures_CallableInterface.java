package AK.ExecutorService_Lecture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * The Future class represents a future result of an asynchronous computation. 
 * This result will eventually appear in the Future after the processing is complete.
 * 
 * 
 	To return values need to implement callable interface instead of runnable
 	Callable<T> - Single method named call returns the result
 	Throws checked Exception
 	Generic argument
 	We have to use executors service submit method to pass a callable 
 	unlike Runnabes , we pass the runnables to the execuor service using execute method
 	
 		- Call to submit returns a future<T>
 		- calling get on the future returns the task result
 		
 		
 		
 		Create a class that implements the Callable (Call method of callable)
 		
 		Use executor.submit(new Callable) this returns a future 
 		
 		Call the get method future.get() to return the result
  		
 		
 		
 		
 		
 */
public class ES_L03_Futures_CallableInterface {
	
	
	
	public static void main (String args[]) {
		
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		Future<?> future1= executor.submit(new Calculate(5));
		Future<?> future2= executor.submit(new Calculate(10));
		
		//You can also submit Runnable to a  submit but it does not return any value in the future
		
		Future<?> future3 = executor.submit(new runnableTask());
		
		//You can pass two parameter to Future to specify the return value upon successful completion
		
		Future<Integer> future4 = executor.submit(new runnableTask(),4528);
		
		//The below is wrong. you cant pass two arguments when you are passing callable as one of the argument
		//Future<?> future5= executor.submit(new Calculate(5),5858);
		
		try {
			System.out.println(future1.get());
			System.out.println(future2.get());
			System.out.println(future3.get());
			System.out.println(future4.get());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	private static class Calculate implements Callable<Integer>{

		
		
		private int result = 1;
		private int ntimes;
		
		public Calculate(int ntimes){
			this.ntimes=ntimes;
		}
		
		@Override
		public Integer call() throws Exception {
			// TODO Auto-generated method stub
			for(int i=1;i<=ntimes;i++) {
				result = result*i;
				
				TimeUnit.SECONDS.sleep(1);
			}
			
			return result;
		}
		
		
		
	}
	
	
	private static  class runnableTask implements Runnable{

		@Override
		public void run() {
			//System.out.println(String.format("Runnable Task Completed"));
			
		}
		
		
	}
	
	
	
	
	
	
	
}

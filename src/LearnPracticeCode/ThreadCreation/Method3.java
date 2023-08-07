package LearnPracticeCode.ThreadCreation;

import java.util.concurrent.TimeUnit;

/*
 * 		in line thread creation, to perform simple threads. Simple UI Events
 */

public class Method3 {

	
	
	public static void main(String args[]) {
		
	
		Thread thread= new Thread(new Runnable() {

			@Override
			public void run() {
				
				for(int i=0; i<=10;i++)
					System.out.println("Task"+i);
			}
		});
		thread.start();
	}
	
	
	
	
}

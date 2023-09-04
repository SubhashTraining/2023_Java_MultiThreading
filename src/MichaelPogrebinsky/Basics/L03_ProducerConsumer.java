package MichaelPogrebinsky.Basics;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;



public class L03_ProducerConsumer {

	
	
	public static void main(String argsp[]) {
		
		Stack<String> productStack = new Stack<>();
		Product producerThread = new Producer(productStack);
		Product consumerThread = new Consumer(productStack);
		
		producerThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consumerThread.start();
		
		
	}
	private static class Product extends Thread {
		
		protected Stack<String> productStack;
		
		protected static boolean produce = true; 
		public Product(Stack<String> productStack) {
			this.productStack= productStack;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}
		
		@Override
		public void start() {
			System.out.println(this.getName()+ " has started");
			super.start();
		}
		
	}
	
	
	private static class Producer extends Product{
		
		public Producer(Stack<String> productStack) {
			super(productStack);
			// TODO Auto-generated constructor stub
		}
		int counter=0;
		@Override
		public void run() {
			synchronized(this.productStack){
				while(produce) {
					
				productStack.push("Item"+ ++counter);
				System.out.println(productStack.peek() + " has been produced ");
					if(counter%5==0) {
					
						try {
							this.productStack.notify();
							this.productStack.wait();
						} 	catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
							}
					}		
				
				}
				System.out.println("Production has stopped");
			}
		}
	}
		
	private static class Consumer extends Product{
		
		public Consumer(Stack<String> productStack) {
			super(productStack);
			// TODO Auto-generated constructor stub
		}
		int counter =0;
		@Override
		public void run()
		{
			synchronized(this.productStack) {
				
				while(produce) {
					
					System.out.println(productStack.pop()+" has been consumed");
					
					if(++counter%5==0) {
						
						try {
							
							this.productStack.notify();
							if(counter%50==0) {
								produce=false;
							}
							else {
								this.productStack.wait();
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					
				}
				
				
			}
		}
		
		
	}
	
}

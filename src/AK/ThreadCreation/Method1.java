package AK.ThreadCreation;

import java.util.concurrent.TimeUnit;

public class Method1 {

	
	
	public static void main(String args[]) {
		
		
		new MyThread().start();
		MyThread thread2 = new MyThread();
		Thread thread3= new MyThread();			
			thread2.start();		
			thread3.start();		
	}
	
	
	private static class MyThread extends Thread{
		
		private static int ID=0;
		public MyThread() {
		this.setName(this.getClass().getSimpleName()+"-"+ ++ID);			
		}
		
		@Override
		public void start() {
			System.out.println(this.getName()+ " has started" );
			super.start();
		}
		
		@Override
		public void run() {
			
			for(int i=1;i<=10;i++) {
				System.out.println(this.getName()+" Task:"+i);
			}
			
		}
		
		
	}
	
}

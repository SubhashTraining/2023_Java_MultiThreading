package MichaelPogrebinsky.Basics;

import java.util.*;
public class L02_Hacker{
	
	private static final int MAX_PASS =9999;
	
	
	
		
	public static void main(String args[]) {
		
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_PASS));
		
		Thread AscendingHackerThread = new AscendingHacker(vault);
		Thread PolicThread = new Police();
		
		
		Thread DescendingHackerThread = new DescendingHacker(vault);
		DescendingHackerThread.start();
		AscendingHackerThread.start();
		PolicThread.start();
		
	}
	
	
	
	
	
	
	
	private static class Vault{
		private int password;
		public Vault(int password) {
		this.password= password;
		}
		
		public boolean checkPassword(int guess) throws InterruptedException {
		Thread.sleep(2);
		return this.password==guess;
		}	
	}
	
	
	private static abstract class HackerThread extends Thread{
		
		protected Vault vault;
		public HackerThread(Vault vault) {
			this.vault= vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}	
		@Override
		public void start() {
			System.out.println("Starting Thread .."+this.getName());
			super.start();
		}
	}
	
	private static class AscendingHacker extends HackerThread{

		public AscendingHacker(Vault vault) {
			super(vault);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			for(int guess=0;guess<=MAX_PASS;guess++) {
				try {
					
					if(vault.checkPassword(guess)) {
						System.out.println(this.getName()+"Hacked the Vault!");
						System.exit(0);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	private static class DescendingHacker extends HackerThread{

		public DescendingHacker(Vault vault) {
			super(vault);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			for(int guess=MAX_PASS;guess>0;guess--) {
				
					try {
						
						if(vault.checkPassword(guess)) {
							System.out.println(this.getName()+"Hacked the Vault!");
							System.exit(0);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}
		
	}
	 
	private static class Police extends Thread{
		
		public void run() {
			for(int count =0;count<=20;count++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Police on the way...");
				
			}
			System.out.println("Police Caught the Hackers!");
			System.exit(0);
		}
		
	}
	

}
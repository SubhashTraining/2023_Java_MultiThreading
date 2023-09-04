package MP_MultiThreading_Lectures;

public class L02_CriticalSectionSharing_Sol1 {

	// Solution by marking the method as synchronized (Not efficient)
	public static void main(String args[]) {
		
		
		Inventory inventory  = new Inventory();
		Thread t1 = new Thread(new IncrementInventory(inventory)) ;
		Thread t2 = new Thread(new DecrementInventory(inventory)) ;
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// The result is not always 0 sometimes its in negative values
		System.out.println(inventory.returnItems());
		
		
		
	}
	
	
	
	
	private static class DecrementInventory implements Runnable{
		
		private Inventory inventory;
		
		public DecrementInventory(Inventory inventory) {
			this.inventory= inventory;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1;i<=1000;i++) {
				this.inventory.decrementItems();
			}
		}
		
		
		
	}
	
	private static class IncrementInventory implements Runnable{

		private Inventory inventory;
		
		public IncrementInventory(Inventory inventory) {
			this.inventory= inventory;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1;i<=1000;i++) {
				this.inventory.incrementItems();
			}
			
		}
		
		
		
	}
	
	private static class Inventory{
		
		private int Items=0;
		
		public synchronized void incrementItems() {
			
			this.Items++;
		}
		public synchronized void decrementItems() {
			this.Items--;
		}
		
		public int returnItems(){
			return this.Items;
		}
		
	}
}

package MP_MultiThreading_Lectures;

public class L01_ResourceSharingProblem {

	
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
		
		/*
		 	The inventory counter is a shared object
		 	The items member is also shared between two threads
		 	
		 	some of the items++ and items-- are happening at the same time 
		 	and not infact a single opertaion at all
		 	
		 	They are not atomic.In high level languages they appear to be a single op 
		 	but its in fact 3 ops
		 	1. Fetch the current value
		 	2. Increment the value
		 	3. Save the value	
		 	
		 	When both threads operate at the same time, when saving the value incrementThread save c
		 	can be overridden by decrementing thread save. Check the doc for more clear picture
		 	
		 	Solution1:
		 	Create the methods as synchronized. This will set an object level lock. if thread a is
		 	accessing increment or decrementItems method. then thread b is not allowed to access any
		 	of these methods
		 	
		 	public synchronized void incrementItems() {}
		 	
		 	public synchronized void decrementItems() {}
		 	
		 	Solution2:
		 	Synchornized block. refer doc
		 
		 */
		
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
		
		public void incrementItems() {
			
			this.Items++;
		}
		public void decrementItems() {
			this.Items--;
		}
		
		public int returnItems(){
			return this.Items;
		}
		
	}
}

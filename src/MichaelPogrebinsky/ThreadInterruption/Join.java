package MichaelPogrebinsky.ThreadInterruption;


/*
 *	What if a thread has started and then it has to wait for another thread to be completed
 *	proceed further. One way is that you can stop and have a loop checking if thread X is completed 
 *	or not. This is a bad approach
 *
 * What if you can put this thread to sleep and wait for thread X to complete.
 * and resume later
 *
 * This can be achieved using join
 * Thread.join()
 * 
 *  public final void join()
 *  public final void join(long millsec, int nanosecs)
 *  public final void join(long millisecs)
 *  
 *  More control over independent threads
 *  Safely collect and aggregate results
 *  Gracefully handle runaway threads
 *  
 *  
 *  WITH THREADS
 *  - do not rely on order of execution
 *  - always use thread coordination
 *  - design code for worst case scneario
 *  - Threads may take unreasonably longer time
 *  - always use thread.join with a time limit
 *  - stop the thread if its not dont in time
 *  
 * @author subhperu
 *
 */
public class Join {

	
	
	
	
}

package SecondYear.week3.dispatcher;

import java.util.LinkedList;
import java.util.Queue;

public class Dispatcher implements Runnable {
	private final Queue<Runnable> queue = new LinkedList<>();
	private volatile boolean cancelled = false;
	private volatile Thread ownThread;
	public synchronized void queueRunnable( Runnable toQueue) {
		this.queue.add( toQueue);
		notifyAll(); // wake up dispatch thread
	} 
	public void cancel() {
		this.cancelled = true;
		this.ownThread.interrupt(); // wake up dispatch thread
	}

	public synchronized void run() {
		this.ownThread = Thread.currentThread();
		while(!this.cancelled) {
			Runnable next = null;
			synchronized(this) {
				next = this.queue.poll();
				if ( next == null) {
					try {
						wait(); // for an item to be added to the queue
					} catch( InterruptedException e) {}
				}
			}      
			if ( next != null) {
				next.run();
			}
		}
	}
}

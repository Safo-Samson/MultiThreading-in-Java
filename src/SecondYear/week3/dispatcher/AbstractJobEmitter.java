package SecondYear.week3.dispatcher;

public abstract class AbstractJobEmitter extends Thread {
  private final Dispatcher dispatcher;
  private final int delayMin;
  private final int delayMax;
  private volatile boolean cancelled;
  
  public AbstractJobEmitter( final Dispatcher dispatcher, final int delayMin, final int delayMax) {
    this.dispatcher = dispatcher;
    this.delayMin = delayMin;
    this.delayMax = delayMax;
  }
  
  public void cancel() {
    this.cancelled = true;
    interrupt();
  }
    
  @Override
  public void run() {
    while( !this.cancelled) {
      int delay = (int)((Math.random() * (this.delayMax - this.delayMin)) + this.delayMin);
      try {
        Thread.sleep(delay);
      } catch( InterruptedException e) {}

      Runnable job = createJob();
      this.dispatcher.queueRunnable( job);
    }
    System.out.println( this + " ending");
  }
  
  protected abstract Runnable createJob();

}

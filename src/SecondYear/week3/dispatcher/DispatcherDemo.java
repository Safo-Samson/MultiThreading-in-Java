package SecondYear.week3.dispatcher;

public class DispatcherDemo {

  public static void main( String[] args) {
    Dispatcher dispatcher = new Dispatcher();
    Thread dispatchThread = new Thread(dispatcher);
    dispatchThread.start();

    TimesTableJobEmitter ttJobEmitter = new TimesTableJobEmitter(dispatcher);
    ttJobEmitter.start();

    ClockJobEmitter clockJobEmitter = new ClockJobEmitter(dispatcher);
    clockJobEmitter.start();

    try {
      Thread.sleep( 60000);
    } catch( InterruptedException e) {
      System.out.println( "Main woke up early");
    }
    ttJobEmitter.cancel();
    clockJobEmitter.cancel();
    dispatcher.cancel();
  }

}

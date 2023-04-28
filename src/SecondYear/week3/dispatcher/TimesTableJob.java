package SecondYear.week3.dispatcher;

public class TimesTableJob implements Runnable {
  private final int table;
  public TimesTableJob( final int table) {
    this.table = table;
  }
  @Override
  public void run() {
    for(int i = 1; i <= 12; i++) {
      System.out.printf( "%2d times %d is %d\n", i, this.table, (i * this.table));
    }
  }
}

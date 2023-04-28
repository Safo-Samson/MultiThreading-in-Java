package SecondYear.week3.dispatcher;

public class TimesTableJobEmitter extends AbstractJobEmitter {
  private int table;
  
  public TimesTableJobEmitter( Dispatcher dispatcher) {
    super( dispatcher, 3000, 8000);
  }

  @Override
  protected Runnable createJob() {
    this.table++;
    if ( this.table > 20) { this.table = 1; }
    return new TimesTableJob(this.table);
  }

}

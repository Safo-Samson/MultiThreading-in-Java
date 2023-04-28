package SecondYear.week3.dispatcher;

public class ClockJobEmitter extends AbstractJobEmitter{

    public ClockJobEmitter(Dispatcher dispatcher) {
        super(dispatcher, 1000, 1000);
    }

    @Override
    protected Runnable createJob() {
        return new ClockJob();
    }
}

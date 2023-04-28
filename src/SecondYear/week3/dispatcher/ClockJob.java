package SecondYear.week3.dispatcher;

import java.util.Date;

public class ClockJob implements Runnable{
    @Override
    public void run() {
        System.out.println("Time now: " + new Date(System.currentTimeMillis()));
    }
}

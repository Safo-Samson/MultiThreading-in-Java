package SecondYear.week2.Counter;

public class CounterDemo {
    private static class DoCount implements Runnable {
        private final Counter counter;
        private final int number;
        public DoCount( final Counter counter, final int number) {
            this.counter = counter;
            this.number = number;
        }
        @Override
        public void run() {
            for( int i = 0; i < this.number; i++) {
                try {
                    Thread.sleep( (long)(Math.random() * 10));
                } catch( InterruptedException e) {}
                this.counter.increment();
            }
        }
    }

    public static void main( final String[] args) throws InterruptedException {
        final Counter counter = new UnsynchronizedCounter(); // uncomment only one of these two lines at a time
        //final Counter counter = new SynchronizedCounter(); // uncomment only one of these two lines at a time

        final Thread[] threads = new Thread[10];
        // create the threads, with each one set to count up to 100
        for( int i = 0; i < threads.length; i++) {
            threads[i] = new Thread( new DoCount(counter, 100));
        }

        // set all the threads running concurrently
        for( int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        // wait for all the threads to finish
        for( int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        // print out the total counted
        // (should be 100 timess the number of threads)
        System.out.println( counter.value());

    }

}


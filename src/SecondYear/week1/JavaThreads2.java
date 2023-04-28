package SecondYear.week1;

public class JavaThreads2 implements Runnable{

    private final long delay;

    public JavaThreads2(long delay){
        this.delay = delay;
    }

    JavaThreads2 dummyThread;
    Thread next = new Thread(dummyThread);

    public JavaThreads2(long delay, Thread next) {
        this.delay = delay;
        if(next!=null)
            this.next = next;
    }

    /*
        long startTime = System.currentTimeMillis();
        double elapsed = (double)(System.currentTimeMillis() - startTime) / 1000.0D;
        System.out.println("Done in " + elapsed + " seconds");  //System.out.printf("Done in % 1.3f ", elapsed);
   */
    /**
     * If t is a Thread object whose thread is currently executing,
     * then t.join() will make sure that t is terminated before the next instruction is executed by the program.
     * */
    @Override
    public void run() {
        if(next!=null) {
            try {
                next.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0;i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + "count " + i);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished\n ");
    }

    public static void main(String[] args) {
        JavaThreads2 t1 ;
        JavaThreads2 t2 = new JavaThreads2(1000);
        JavaThreads2 t3 = new JavaThreads2(2000);
        JavaThreads2 t4 ;

        Thread thread2 = new Thread(t2, "Thread-1");
        Thread thread3 = new Thread(t3, "Thread-2");

        t1 = new JavaThreads2(500,thread2);
        t4 = new JavaThreads2(3000,thread3);

        Thread thread1 = new Thread(t1, "Thread-0");
        Thread thread4 = new Thread(t4, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

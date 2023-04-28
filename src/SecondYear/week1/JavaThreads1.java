package SecondYear.week1;

public class JavaThreads1 implements Runnable{

    private final long delay;

    public JavaThreads1(long delay){
        this.delay = delay;
    }

    @Override
    public void run() {
        for(int i = 0;i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + "count " + i);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished\n");
    }

    public static void main(String[] args) {


        Thread thread1 = new Thread(new JavaThreads1(500), "Thread 1");
        Thread thread2 = new Thread(new JavaThreads1(1000), "Thread 2");
        Thread thread3 = new Thread(new JavaThreads1(2000), "Thread 3");
        Thread thread4 = new Thread(new JavaThreads1(3000), "Thread 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

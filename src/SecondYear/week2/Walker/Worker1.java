package SecondYear.week2.Walker;

import java.io.File;

import static SecondYear.week2.Walker.Utilities.*;
public class Worker1 implements Runnable{
    // volatile guarantees memory consistency and makes the volatile variable updated to all threads
    // if a variable is  not volatile and is called in a method, that method may not see the current/true value of the variable
    // even if it has been updated because each method might be viewing different version of cached memory

    private volatile boolean finished;
    private FileWalker walker;

    public Worker1(final FileWalker walker){
        this.walker = walker;
    }

    public void setWalker(FileWalker walker){
        this.walker = walker;
    }


    public boolean isFinished(){
        return this.finished;
    }

    public boolean doNextFile(FileWalker walker) {
        final File f1 = walker.nextFile();
        if ( f1 == null) {
            return false;
        } else {
            System.out.println( f1 + "\t" + toHexString(computeHash(f1)));
            return true;
        }
    }

    public void doWorkForLongTime(){
        while(!finished && !Thread.currentThread().isInterrupted()){
            finished = !doNextFile(walker); // return the inverse of the boolean returned
        }
        if(finished){
            System.out.println("Worker is done!");
        }else{
            System.out.println("Main Cancelled");
            finished = true;
        }
    }

    @Override
    public void run() {
        doWorkForLongTime();
    }
}


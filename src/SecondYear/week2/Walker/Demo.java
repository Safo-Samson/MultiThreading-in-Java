package SecondYear.week2.Walker;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static SecondYear.week2.Walker.Utilities.*;
public class Demo {
    public static boolean doNextFile(final FileWalker walker) {
        final File f1 = walker.nextFile();
        if ( f1 == null) {
            return false;
        } else {
            System.out.println( f1 + "\t" + toHexString(computeHash(f1)));
            return true;
        }
    }

    public static void main(final String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        // File target = new File(".");
       final File target = new File("C:\\");

        final FileWalker walker = new FileWalker(target);

//        boolean notLast = true;
//        while(notLast) {
//            notLast = doNextFile(walker);
//        }

        final Worker worker = new Worker(walker);
//        final Worker1 worker1 = new Worker1(walker);
        //worker.setWalker(walker); No need for setter if you have a constructor to do so.
        final Thread thread = new Thread(worker);
        thread.start();


        int attempted = 0;
        while(!worker.isFinished()){
            System.out.println("\nOnGoing...");
            attempted++;
            if(attempted > 3){
                System.out.println("\nCancelling...");
                worker.cancel();
            }
            thread.join(3000);
            System.err.println("Count " + attempted);
            //Thread.sleep(1000); // doesn't give expected output
        }

    // using interrupt without explicitly using a variable like "volatile cancel"  may seem like a better approach but unfortunately,
    // the specification of thread indicates that threads can be spuriously interrupted for no reason  at anytime from anywhere.
//  todo works well
//        while(!worker1.isFinished()){
//            System.out.println("\nOnG");
//            thread.join(2000);
//            attempted++;
//            if(attempted >2 ){
//                thread.interrupt();
//                System.out.println("Main Cancelling");
//               // thread.stop(); // not needed cah the interrupt signal will kill it
//            }
//        }

//        while(thread.isAlive() && !thread.isInterrupted()){
//            System.out.println("\nOnG");
//            thread.join(2000);
//            attempted++;
//            if(attempted == 2 && thread.isAlive()){
//                thread.interrupt();
//                System.out.println("Main Cancelling");
//                thread.stop();
//            }
//        }

        System.out.println("Done");
    }
}
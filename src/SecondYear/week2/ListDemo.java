package SecondYear.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShowLast implements Runnable{
    private final List<String> list;
    public ShowLast(final List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            synchronized (this.list) {
                if (this.list.size() > 0) {
                    int lastItem = this.list.size() - 1;
                    Thread.yield(); //encourage errors lol, current thread asks OS for another thread to go before it
                    System.out.println("last item is " + this.list.get(lastItem));
                } else break;
            }
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Remover implements Runnable{
    private final List<String> list;
    public Remover(final List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {}

            synchronized (this.list){
                if(list.size()>0){
                System.out.println("Removing " + this.list.remove(this.list.size()-1));;
            }
                else break;
            }
        }
    }
}

public class ListDemo {
    public static void main(String[] args) throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("apple","banana","lemon","pineapple","lime","pear","plum",
                "peach","raspberry","strawberry","gooseberry","melon","kiwi"));
        final Thread[] threads = new Thread[4];

        for(int i =0;i<= threads.length/2;i+=2){
            threads[i] = new Thread(new Remover(list));
            threads[i+1] = new Thread(new ShowLast(list));
        }
        for(int i =0;i<threads.length;i++){
            threads[i].start();
        }
        for(int i =0;i<threads.length;i++){
            threads[i].join();
        }
    }

}

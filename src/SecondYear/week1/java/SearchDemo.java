package SecondYear.week1.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import adp.lab1.SearchFileManager;
import adp.lab1.SearchFileManager.Searcher;

public class SearchDemo {
  /**
   * A simple single-threaded implementation of Searcher which simply
   * calls {@link SearchFileManager#searchFile(File, byte[])} for each
   * file in the array in turn.
   */
  public static class SimpleSearcher implements Searcher {
    @Override
    public void searchFiles( File[] files, byte[] sequence) {
      for( File file : files) {
        SearchFileManager.searchFile( file, sequence);
      }
    } 
  }
  
  /**
   * This class should be completed to perform a multithreaded search
   * using one thread for each file to be searched.
   */
  public static class MultiThreadedSearcher implements Searcher {

    /**
     * This method needs to be implemented to
     * - create a Thread for each file in the given files array
     * - for each Thread give it an instance of Runnable that will perform the file search 
     *   using the {@link SearchFileManager#searchFile( File, byte[])}
     * - start each thread running
     * - wait for each thread to complete by executing join() on each of them in turn
     * (You will need to store the Thread objects in an array or something similar in
     *  order to perform the join() calls on them, after starting them all).
     */
    @Override
    public void searchFiles( File[] files, final byte[] sequence)  {
      //throw new RuntimeException( "Method has not been implemented: YOU HAVE TO DO THIS!");

      //creating ArrayList of threads
      List<Thread> threadArray = new ArrayList<>();
      for(File file : files){
        //using lambda to implement runnable
        Thread thread = new Thread(() -> SearchFileManager.searchFile( file, sequence));
        thread.start();
        threadArray.add(thread);
      }

      for(Thread thread : threadArray){
        try {
          thread.join(); // main thread will wait for each thread to finish
        } catch (InterruptedException e) {
          System.out.println(thread.getName() + " was interrupted");
        }
      }

    }
  }
  
  public static void main( String[] args) throws IOException {
    
    // use this variable to switch between searchers
    //final boolean useMultiThreadSearch = false;
    final boolean useMultiThreadSearch = true;

    Searcher searcher = null;
    if ( useMultiThreadSearch) {
      System.out.println( "Using multi-threaded searching");
      searcher = new MultiThreadedSearcher();
    } else {
      System.out.println( "Using single-threaded searching");
      searcher = new SimpleSearcher();
    }
    
    SearchFileManager manager = new SearchFileManager( 8, 10, 500000);
    manager.doSearch( searcher);    
    
  }
  
}

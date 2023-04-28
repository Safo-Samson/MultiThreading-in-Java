package SecondYear.week6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Files {

  private static List<File> listHardDrive() {
    ArrayList<File> output = new ArrayList<>(10000);
    File root = new File("C:\\Program Files");
    ArrayList<File> toDo = new ArrayList<File>();
    toDo.add( root);
    while( !toDo.isEmpty()) {
      File file = toDo.remove(0);
      //System.out.println( file);
      output.add( file);
      if ( file.isDirectory()) {
        File[] files = file.listFiles();
        if ( files != null) {
          toDo.addAll( Arrays.asList( files));
        }
      }    
    }
    return output;
  }

  public static void main( String[] args) {
    List<File> files = listHardDrive();
    
    System.out.println( "Modified today:");
    long now = System.currentTimeMillis();
//    for( File file : files) {
//      if ( now - file.lastModified() < 86400000) {
//        System.out.println( file);
//      }
//    }
//

    files.forEach((file)->{if ( now - file.lastModified() < 86400000) System.out.println( file);});
    
    System.out.println( "Greater than 100 MB:");
//    for( File file : files) {
//      if ( file.length() > 104857600) {
//        System.out.println( file);
//      }}

      files.forEach((file)->{if ( file.length() > 104857600)  System.out.println( file); });


    }
    
  }

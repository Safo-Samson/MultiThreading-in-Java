package SecondYear.week6;

import javax.swing.*;
import java.awt.*;


public class GraphComponentDemo extends JFrame {
  private static final long serialVersionUID = 1L;
  
  private final GraphComponent component = new GraphComponent();

  public GraphComponentDemo() {
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
      
//    this.component.addLine((x)-> Math.sin(x), Color.blue);
    this.component.addLine(Math::sin, Color.blue);
    this.component.addLine((x)-> 3 * Math.sin(x * 3), Color.red);
    this.component.addLine((x)-> 2 * Math.sin(x * 2), Color.green);


    

    
    add(this.component);
    pack();
    setVisible(true);
  }


  public static void main( final String[] args) {
    SwingUtilities.invokeLater( new Runnable() {
      @Override
      public void run() {
        new GraphComponentDemo();
      }
    });
  }

}

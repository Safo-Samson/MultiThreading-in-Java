
package SecondYear.week3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Long-running jobs need to be executed on a separate thread to allow the
 * event dispatch thread to remain responsive.
 */
public class Gui3 extends JFrame {
  private final JButton button = new JButton( "Press me");
  public Gui3() {
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(this.getClass().getSimpleName());
    add( this.button);
    this.button.addActionListener(ev -> doButton());
    pack();
    setSize(300,getHeight());
    setVisible( true);
  }
  
  private void doButton() {
    new Thread(() -> {
      for( int i = 0; i < 20; i++) {
        System.out.println( "Ouch! " + i);
        try { Thread.sleep( 500); } catch( InterruptedException x) {}
      }
      System.out.println();
    }).start();
  }

  public static void main( final String[] args) {
    SwingUtilities.invokeLater(() -> new Gui3());
  }
}

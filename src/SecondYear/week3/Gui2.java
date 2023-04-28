package SecondYear.week3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A long running job executed on the event dispatch thread causes the UI to
 * become unresponsive; the button stays down, the window cannot be closed and
 * window resizes do not repaint properly until the long running job has finished.
 */
public class Gui2 extends JFrame {
  private final JButton button = new JButton( "Press me");
  public Gui2() {
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(this.getClass().getSimpleName());
    add( this.button);
    this.button.addActionListener(ev -> doButton());
    pack();
    setSize(300,getHeight());
    setVisible( true);
  }

  private void doButton() {
    for( int i = 0; i < 10; i++) {
      System.out.println( "Ouch! " + i);
      try { Thread.sleep( 500); } catch( InterruptedException x) {}
    }
    System.out.println();
  }

  public static void main( final String[] args) {
    SwingUtilities.invokeLater(() -> new Gui2());
  }
}

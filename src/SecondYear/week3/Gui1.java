package SecondYear.week3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Fast executing job can be executed directly on the event dispatch without problems.
 */
public class Gui1 extends JFrame {
  private final JButton button = new JButton( "Press me");
  public Gui1() {
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(this.getClass().getSimpleName());
    add( this.button);
    this.button.addActionListener((actionEvent) -> doButton());
    pack();
    setSize(300,getHeight());
    setVisible( true);
  }

  private void doButton() {
    System.out.println( "Ouch!");
  }

  public static void main( final String[] args) {
    SwingUtilities.invokeLater(() -> new Gui1());
  }
}

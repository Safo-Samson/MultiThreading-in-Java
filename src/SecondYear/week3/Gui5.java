
package SecondYear.week3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Long-running job scheduling millions of updates on the event dispatch
 * queue with no delay between them - UI becomes unresponsive because queue
 * is saturated with jobs leaving little space for repaints.
 */
public class Gui5 extends JFrame {
  private final JButton button = new JButton( "Press me");
  private final JLabel infoLabel = new JLabel( "Ready...");
  public Gui5() {
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(this.getClass().getSimpleName());
    JPanel panel = new JPanel( new GridLayout(2,0));
    panel.add( this.button);
    panel.add( this.infoLabel);
    add( panel);
    this.button.addActionListener(ev -> doButton());
    pack();
    setSize(300,getHeight());
    setVisible( true);
  }
  
  private void doButton() {
    new Thread(() -> {
      for( int i = 0; i < 10000000; i++) {
        queueInfo( "Ouch " + i + "!");
        //try { Thread.sleep( 500); } catch( InterruptedException x) {}
      }
      queueInfo( "Done...");
    }).start();
  }

  public void queueInfo( final String info) {
    SwingUtilities.invokeLater(() -> setInfo( info));
  }
  
  public void setInfo( String info) {
    this.infoLabel.setText( info);
  }
  
  public static void main( final String[] args) {
    SwingUtilities.invokeLater(() -> new Gui5());
  }
}

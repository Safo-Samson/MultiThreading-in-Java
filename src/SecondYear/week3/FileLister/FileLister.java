package SecondYear.week3.FileLister;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class FileLister extends JFrame {

    JButton button = new JButton( "List files");
    JList list = new JList( new DefaultListModel());

    public FileLister() throws HeadlessException {

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel( new BorderLayout());
        JScrollPane sp = new JScrollPane( this.list);

        panel.add( sp, BorderLayout.CENTER);
        panel.add( this.button, BorderLayout.SOUTH);

        add( panel);

      //  this.button.addActionListener(e -> listHardDrive());
        this.button.addActionListener(e -> listHardDrive2());

        setSize( 800, 600);
        setVisible( true);
    }

    private void listHardDrive() {
        File root = new File("C:\\Program Files");
        ArrayList<File> toDo = new ArrayList<>();
        toDo.add( root);
        while( !toDo.isEmpty()) {
            File file = toDo.remove(0);
            System.out.println( file);
            appendFile( file);
            if ( file.isDirectory()) {
                File[] files = file.listFiles();
                if ( files != null) {
                    toDo.addAll( Arrays.asList( files));
                }
            }
        }
    }
    private void listHardDrive2() { //todo modification to the listHardDrive to use threads

        new Thread(() -> {File root = new File("C:\\Program Files");
        ArrayList<File> toDo = new ArrayList<>();
        toDo.add( root);
        while( !toDo.isEmpty()) {
            File file = toDo.remove(0);
            System.out.println( file);
            fileQueue(file); //todo call fileQueue to do it rightly
           // appendFile( file);
            if ( file.isDirectory()) {
                File[] files = file.listFiles();
                if ( files != null) {
                    toDo.addAll( Arrays.asList( files));
                }
            }
        }}
        ).start();
    }

    public void fileQueue(File file) {
        try {
            SwingUtilities.invokeAndWait(() -> appendFile(file));
        } catch (InvocationTargetException | InterruptedException e) {
        }
    }


    private void appendFile( File file) {
        DefaultListModel model = (DefaultListModel)this.list.getModel();
        model.addElement( file);
        int lastIndex = model.getSize() - 1;
        if (lastIndex >= 0 && (lastIndex % 100 == 0)) {
            // note the mod 100 is to avoid saturating the AWT thread with scroll requests
            list.ensureIndexIsVisible(lastIndex);
        }
    }

    public static void main( final String[] args) {
        SwingUtilities.invokeLater(() -> new FileLister());
    }
}

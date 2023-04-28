package SecondYear.week6;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GraphComponent extends JComponent {
  private static final long serialVersionUID = 1L;
  private final GraphImage graph;

  public GraphComponent() {
    this.graph = new GraphImage();
    setPreferredSize(new Dimension(800, 400));
  }

  public void setXRange( double xStart, double xEnd) {
    if ( xStart != this.graph.xStart || xEnd != this.graph.xEnd) {
    this.graph.xStart = xStart;
    this.graph.xEnd = xEnd;
    this.graph.image = null;
    repaint();
    }
  }
  
  public void addLine( Line line) {
    this.graph.addLine( line);
  }
  public void addLine( Formular formular, Color color) {
    this.graph.addLine(new Line(formular,color));
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = g.create();
    g.drawImage(this.graph.render( this.getSize()), 0, 0, this);
  }

  private static class GraphImage {

    private BufferedImage image;
    private final Color background = Color.WHITE;

    private double xStart = 0;
    private double xEnd = Math.PI * 4;

    private final List<Line> lines = new ArrayList<>();

    public void addLine( final Line line) {
      this.lines.add( line);
    }

    public BufferedImage render( final Dimension size) {
      if ((this.image == null) || (this.image.getWidth() != size.width) || (this.image.getHeight() != size.height)) {
        this.image = new BufferedImage( size.width, size.height, BufferedImage.TYPE_INT_RGB);

        final int xPixels = size.width;
        final int yPixels = size.height;

        // compute true values for Y for each pixel
        for(final Line line : this.lines) {
          line.computeYValues( this.xStart, this.xEnd, xPixels);
        }

        double max = -Double.MAX_VALUE;
        double min = Double.MAX_VALUE;
        // find max and minimum y values for all lines
        for( final Line line : this.lines) {
          if ( line.max > max) { max = line.max; }
          if ( line.min < min) { min = line.min; }
        }
        // compute scaled y coordinates for each pixel
        for( final Line line : this.lines) {
          line.computeScaledYValues( min, max, yPixels);
        }

        final Graphics2D g = (Graphics2D)this.image.getGraphics();
        // clear image
        g.setColor( this.background);
        g.fillRect(  0, 0, xPixels, yPixels);

        // draw graph
        for( final Line line : this.lines) {
          g.setColor( line.color);
          for( int i = 0; i < line.pixelYValues.length - 1; i++) {
            g.drawLine( i, line.pixelYValues[i], i+1, line.pixelYValues[i+1]);
          }
        }
      }
      return this.image;
    }
  }
  
  private static class Line {
    private final Formular formular;
    private final Color color;
    private double[] values;
    private double max;
    private double min;
    private int[] pixelYValues;

//    public Line( final Color color) {
//      this.color = color;
//    }

    public Line( final Formular formular, Color color) {
      this.color = color;
      this.formular = formular;
    }
//
//    public abstract double evaluate( double x);
    public double evaluate(double x){
      return formular.evaluate(x);
    }

    public void computeYValues( final double xStart, final double xEnd, final int xPixels) {
      this.max = -Double.MAX_VALUE;
      this.min = Double.MAX_VALUE;

      // compute function values for each x pixel column
      final double step = (xEnd - xStart) / xPixels;
      this.values = new double[xPixels];
      for( int x = 0; x < xPixels; x++) {
        final double value = evaluate( x * step);
        if ( value < this.min) { this.min = value; }
        if ( value > this.max) { this.max = value; }
        this.values[x] = value;
      }
    }

    public void computeScaledYValues( final double minY, final double maxY, final int yPixels) {
      // scale y values to fit height
      final double yRange = maxY - minY;
      this.pixelYValues = new int[this.values.length];
      for( int i = 0; i <this.values.length; i++) {
        this.pixelYValues[i] = (int)(yPixels * ((this.values[i] - minY) / yRange));
      }
    }
  }

}
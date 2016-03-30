import QtJPlotter.*;

import java.util.*;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class callPlotter
{
    private int MAX = 200;
    private double[] a = new double[MAX];
    private double[] b = new double[MAX];
    private Random fRandom = new Random();

    public double getGaussian(double aMean, double aVariance)
    {
	return aMean + fRandom.nextGaussian() * aVariance;
    }

    double MEAN1 = 200.0f; 
    double VARIANCE1 = 20.0f;
    double MEAN2 = 300.0f; 
    double VARIANCE2 = 30.0f;

    static public void main(String args[])
    {
	final QApplication app = new QApplication(args);
	PlotWidget plotter = new PlotWidget();
	callPlotter c = new callPlotter();

	for (int i = 0; i < c.MAX; i++)
	    {
		c.a[i] = c.getGaussian(c.MEAN1, c.VARIANCE1);
		c.b[i] = 5.0 - 2.0*c.a[i] + c.getGaussian(c.MEAN2, c.VARIANCE2);
	    }

	plotter.Plotter(c.a, c.b);
	app.exec();
    }
}

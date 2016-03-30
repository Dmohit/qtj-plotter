package QtJPlotter;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class PlotWidget extends QWidget
{
    private QGraphicsView view = new QGraphicsView();
    private QGraphicsScene scene = new QGraphicsScene();
    
    public void Plotter(double x[], double y[])
    {
	setWindowTitle(tr("QTJambi Plot"));
	view.setScene(scene);
	scene.setBackgroundBrush(new QBrush(QColor.white));

	for(int i = 0; i < x.length; i++)
	    scene.addEllipse(x[i], y[i], 5, 5);

	view.show();
    }    
}

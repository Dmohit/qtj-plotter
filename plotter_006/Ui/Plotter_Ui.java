import com.trolltech.qt.QVariant;
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Plotter_Ui extends QMainWindow {

    private String curFile;
    private KPlotWidget kplotwidget;
    private QMenu fileMenu;
    private QMenu helpMenu;

    private QAction saveAsAct;
    private QAction exitAct;
    private QAction aboutAct;
    private QAction aboutQtJambiAct;
    private QAction aboutQtAct;

    private String rsrcPath = "classpath:com/trolltech/images";

    public Plotter_Ui()
    {
        QMenuBar menuBar = new QMenuBar();
        setMenuBar(menuBar);

        setWindowIcon(new QIcon("classpath:com/trolltech/images/qt-logo.png"));

        kplotwidget = new KPlotWidget();
        setCentralWidget(kplotwidget);

        try {
            createActions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createMenus();
        createToolBars();
        createStatusBar();

        setCurrentFile("");
    }

    public void closeEvent(QCloseEvent event)
    {
        if (maybeSave()) 
	    event.accept();
	else 
	    event.ignore();
    }

    public void about()
    {
        QMessageBox.about(this,
                         tr("About Application"),
                         tr("The <b>Application</b> example demonstrates how to " +
                            "write modern GUI applications using Qt, with a menu bar, " +
                            "toolbars, and a status bar."));
    }

    private void createActions()
    {
        saveAsAct = new QAction(new QIcon(rsrcPath + "/save.png"), tr("Save &As..."), this);
        saveAsAct.setShortcut(tr("Ctrl+S"));
        saveAsAct.setStatusTip(tr("Save the plot to disk"));
        saveAct.triggered.connect(this, "saveAs()");

        exitAct = new QAction(tr("E&xit"), this);
        exitAct.setShortcut(tr("Ctrl+Q"));
        exitAct.setStatusTip(tr("Exit the application"));
        exitAct.triggered.connect(this, "close()");

        aboutAct = new QAction(tr("&About"), this);
        aboutAct.setStatusTip(tr("Show the application's About box"));
        aboutAct.triggered.connect(this, "about()");

        aboutQtJambiAct = new QAction(tr("About &Qt Jambi"), this);
        aboutQtJambiAct.setStatusTip(tr("Show the Qt Jambi library's About box"));
        aboutQtJambiAct.triggered.connect(QApplication.instance(), "aboutQtJambi()");

        aboutQtAct = new QAction(tr("About Q&t"), this);
        aboutQtAct.setStatusTip(tr("Show the Qt library's About box"));
        aboutQtAct.triggered.connect(QApplication.instance(), "aboutQt()");
    }

    private void createMenus()
    {
        fileMenu = menuBar().addMenu(tr("&File"));
        fileMenu.addAction(saveAsAct);
        fileMenu.addSeparator();
        fileMenu.addAction(exitAct);

        menuBar().addSeparator();

        helpMenu = menuBar().addMenu(tr("&Help"));
        helpMenu.addAction(aboutAct);
        helpMenu.addSeparator();
        helpMenu.addAction(aboutQtJambiAct);
        helpMenu.addAction(aboutQtAct);
    }

    private void createStatusBar()
    {
        statusBar().showMessage(tr("Ready"));
    }

    public boolean saveAs()
    {
        String fileName = QFileDialog.getSaveFileName(this);
        if (fileName.length() == 0)
            return false;

        return saveFile(fileName);
    }

    private boolean maybeSave()
    {
	QMessageBox.StandardButton ret = QMessageBox.warning(this, tr("QtJambi Plotter"),
							     tr("Do you want to save this plot?"),
							     new QMessageBox.StandardButtons(QMessageBox.StandardButton.Ok,
											     QMessageBox.StandardButton.Discard,
											     QMessageBox.StandardButton.Cancel));
	if (ret == QMessageBox.StandardButton.Ok) {
	    return saveAs();
	} else if (ret == QMessageBox.StandardButton.Cancel) {
	    return false;
	}
        return true;
    }

    public boolean saveFile(String fileName)
    {
        QFile file = new QFile(fileName);
        if (!file.open(new QFile.OpenMode(QFile.OpenModeFlag.WriteOnly, QFile.OpenModeFlag.Text))) {
            QMessageBox.warning(this, tr("Application"), 
				String.format(tr("Cannot write file %1$s:\n%2$s."), fileName, file.errorString()));
            return false;
        }

        // QTextStream out = new QTextStream(file);
        QApplication.setOverrideCursor(new QCursor(Qt.CursorShape.WaitCursor));
        // out.writeString(textEdit.toPlainText());
        QApplication.restoreOverrideCursor();

        setCurrentFile(fileName);
        statusBar().showMessage(tr("File saved"), 2000);
        file.close();
        return true;
    }

    private static String strippedName(String fullFileName)
    {
        return new QFileInfo(fullFileName).fileName();
    }

    public static void main(String[] args) {
        final QApplication app = new QApplication(args);

        Plotter plotter = new Plotter();
	plotter.resize(500,500);
        plotter.show();

        app.exec();
    }

}
    // public void setupUi(QMainWindow MainWindow)
    // {
    //     MainWindow.setObjectName("MainWindow");
    //     MainWindow.resize(new QSize(525, 516).expandedTo(MainWindow.minimumSizeHint()));
    //     centralwidget = new QWidget(MainWindow);
    //     centralwidget.setObjectName("centralwidget");
    //     kplotwidget = new KPlotWidget(centralwidget);
    //     kplotwidget.setObjectName("kplotwidget");
    //     kplotwidget.setGeometry(new QRect(10, 10, 501, 451));
    //     MainWindow.setCentralWidget(centralwidget);
    //     menubar = new QMenuBar(MainWindow);
    //     menubar.setObjectName("menubar");
    //     menubar.setGeometry(new QRect(0, 0, 525, 25));
    //     MainWindow.setMenuBar(menubar);
    //     statusbar = new QStatusBar(MainWindow);
    //     statusbar.setObjectName("statusbar");
    //     MainWindow.setStatusBar(statusbar);
    //     retranslateUi(MainWindow);

    //     MainWindow.connectSlotsByName();
    // } // setupUi



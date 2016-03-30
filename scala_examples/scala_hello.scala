import com.trolltech.qt.gui._

class MyMainWindow extends QWidget
{
  def showWindow =
  {
    setWindowTitle("Scala Jambi Test")
    resize(250, 250)
    move(300, 300)
    show()
  }
}

object MainApp
{
  def main(args: Array[String])
  {
    val app = new QApplication(args)
    new MyMainWindow().showWindow
    app.exec
  }
}

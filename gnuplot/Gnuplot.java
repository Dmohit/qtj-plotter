import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.lang.StringBuilder;

public class Gnuplot
{
    private int MAX = 8;
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

    public void generate()
    {
	for (int i = 0; i < MAX; i++)
	    {
		a[i] = getGaussian(MEAN1, VARIANCE1);
		b[i] = 5.0 - 2.0*a[i] + getGaussian(MEAN2, VARIANCE2);
	    }
    }

    String command;

    public void buildCommand()
    {
	StringBuilder s = new StringBuilder("echo ");
	
	for (int i = 0; i < MAX; i++)
	    {
		s.append(a[i]).append(" ").append(b[i]);
		if(i < MAX - 1) s.append("\n");
	    }

	s.append(" | feedgnuplot\n");
	
	command = s.toString();
    }
    
    public static void main(String[] args)
    {

	Gnuplot gobj = new Gnuplot();

	gobj.generate();

	gobj.buildCommand();

	System.out.println(gobj.command);
	
	String output = gobj.plot();

	//System.out.println(output);

    }

    public String plot()
    {

	StringBuffer output = new StringBuffer();

	Process p;
	try
	    {
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		BufferedReader reader = 
		    new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line = "";			
		while ((line = reader.readLine())!= null)
		    output.append(line + "\n");
	    }
	catch (Exception e)
	    {
		e.printStackTrace();
	    }

	return output.toString();

    }   
}

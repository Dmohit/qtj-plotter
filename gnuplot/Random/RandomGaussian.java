package Random;

import java.util.Random;

public final class RandomGaussian 
{
    private int MAX = 20;
    private double[] numbers = new double[MAX];
    private Random fRandom = new Random();
    private int i = -1;
    private int j = -1;
  
    public double getGaussian(double aMean, double aVariance)
    {
	return aMean + fRandom.nextGaussian() * aVariance;
    }

    public void onemore(double n)
    {
	if(i == -1)
	    j++;
	i++;
	numbers[i] = n;
    }

    public double getnext()
    {
	if(i == -1) //Empty Array
	    return -1;
	else if(j >= MAX)
	    {
		j = 0;
		return numbers[j];
	    }
	else
	    {
		j++;
		return numbers[j-1];
	    }
    }
} 

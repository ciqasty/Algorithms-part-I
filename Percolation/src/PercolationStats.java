import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

	private double mean=0;
	private double standardDeviation;
	private double confidenceLo;
	private double confidenceHi;
	
	public PercolationStats(int n, int trials)
	{
		//array for percolation threshold values
		double[] x = new double[trials];
		//loop to perform trials
		for (int i=1;i<=trials;i++)
		{
			Percolation currentPercolation = new Percolation(n);
			//loop until grid percolates
			while(!currentPercolation.percolates())
			{
				int row,col;
				row=StdRandom.uniform(n)+1;
				col=StdRandom.uniform(n)+1;
				currentPercolation.open(row, col);
			}
			//compute and store percolation threshold value
			x[i-1]=(double)currentPercolation.numberOfOpenSites()/(n*n);
		}
		//compute mean
		mean = StdStats.mean(x);
		//compute standard deviation
		standardDeviation=StdStats.stddev(x);
		//compute confidence
		confidenceLo=mean-((1.96*standardDeviation)/Math.sqrt(trials));
		confidenceHi=mean+((1.96*standardDeviation)/Math.sqrt(trials));	
	}
	public double mean()
	{
		return mean;
	}
	public double stddev()
	{
		return standardDeviation;
	}
	public double confidenceLo()
	{
		return confidenceLo;
	}
	public double confidenceHi()
	{
		return confidenceHi;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=Integer.parseInt(args[0]);
		int trials=Integer.parseInt(args[1]);
		Stopwatch timer = new Stopwatch();
		PercolationStats test = new PercolationStats(n, trials);
		System.out.println("N: "+n+" T: "+trials);
		System.out.println("mean = "+test.mean());
		System.out.println("stdev = "+test.stddev());
		System.out.println("95% confidence interval = ["+test.confidenceLo()+" , "+test.confidenceHi()+"]");
		System.out.println("Time elapsed: "+timer.elapsedTime());
	}

}

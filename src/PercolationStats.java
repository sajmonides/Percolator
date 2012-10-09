import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class PercolationStats 
{
	private Random r;
	private ArrayList<Point> al; //<- fill with all possible points
	private Percolation p;
	private int N;
	public int T;
	public int[] counts;
	public double[] times;
	
	public PercolationStats(int N, int T)
	{
		this.N = N;
		this.T = T;
		counts = new int[T];
		times = new double[T];
		r = new Random();
		
		Stopwatch s;
		for (int i = 0; i < T; i++)
		{
			p = new Percolation(N);
			al = new ArrayList<Point>();
			
			for (int a = 0; a < N; a++)
				for (int b = 0; b < N; b++)
					al.add(new Point(a+1,b+1));
			
			int index;
			Point point;
			s = new Stopwatch();
			while (!p.percolates())
			{
				index = r.nextInt(al.size());
				point = al.get(index);
				al.remove(index);
				
				p.open(point.x, point.y);	
			}			
			times[i] = s.elapsedTime();
			counts[i] = p.getOpenSiteCount();			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
			int N = StdIn.readInt();
			int T = StdIn.readInt();
		
			PercolationStats ps = new PercolationStats(N,T);
				
			double[] temp = new double[ps.T];
			for (int i = 0; i < ps.T; i++)
			{
				temp[i] = (double)ps.counts[i]/(ps.N*ps.N);
			}
						
			StdOut.printf("mean percolation threshold\t= %7.16f\n", StdStats.mean(temp));
			StdOut.printf("stddev\t\t\t\t= %7.16f\n", StdStats.stddev(temp));
			StdOut.printf("95 confidence interval\t\t= [ %7.16f,", StdStats.mean(temp)+(1.96*StdStats.stddev(temp)/Math.sqrt(ps.T)));
			StdOut.printf(" %7.16f ]\n", StdStats.mean(temp)+(1.96*StdStats.stddev(temp)/Math.sqrt(ps.T)));
			StdOut.printf("total time\t\t\t= %7.16f \n", StdStats.sum(ps.times));
			StdOut.printf("mean time per experiment\t= %7.16f\n", StdStats.mean(ps.times));
			StdOut.printf("stddev\t\t\t\t= %7.16f\n", StdStats.stddev(ps.times)); 
	}


}



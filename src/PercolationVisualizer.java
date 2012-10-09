
public class PercolationVisualizer 
{
	public Percolation p;
	private int N;
	private double s;

	public PercolationVisualizer(Percolation p)
	{
		this.p = p;
		this.N=p.getN();
		s=1/((double)N*2);
	}
	
	public PercolationVisualizer(int N)
	{
		this.N = N;
		s = 1/((double)N*2);
		p = new Percolation(N);
		
		StdDraw.clear(StdDraw.BLACK);
		
	}
	
	public void open(int i, int j)
	{
		p.open(i, j);
		
		if (i == 1)
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		else
			StdDraw.setPenColor(StdDraw.WHITE);
		
		drawSite(i, j);			
	}
	
	public void drawPercolation()
	{
		p.fill();
		
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (p.isOpen(i+1, j+1) && p.isFull(i+1, j+1))
				{
					drawSite(i+1, j+1);
				}
			}
		}
	}
	
	public void drawSite(double i, double j)
	{
		StdDraw.filledSquare(((j-1)/(double)N)+s, 1-(((i-1)/(double)N)+s), s-0.002);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		int N = StdIn.readInt();
		
		PercolationVisualizer pv = new PercolationVisualizer(N);
		
		while (!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			pv.open(p+1, q+1);
		}
		
		pv.drawPercolation();
		
	}

}

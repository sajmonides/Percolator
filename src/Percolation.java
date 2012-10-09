public class Percolation 
{	
	private WeightedQuickUnionUF uf;
	private boolean[][] grid;
	private boolean[][] fullSite;
	private int N;
	private int count;
	private int openSiteCount;
	private boolean percolated;
	
	public Percolation(int N)
	{
		if (N < 0) throw new IllegalArgumentException();
		
		this.N = N;
		count = N*N;
		uf = new WeightedQuickUnionUF(count);
		grid = new boolean[N][N];	
		fullSite = new boolean[N][N];		
	}
	
	public void open(int i, int j)
	{
		if (i < 0 || j < 0 || i > N || j > N ) throw new IndexOutOfBoundsException();
		
		int i2 = i-1;
		int j2 = j-1;
		
		if (!grid[i-1][j-1])
		{
			openSiteCount++;
		}
		
		grid[i2][j2] = true;
			
		if (i == 1) //Fill the top row.
			fullSite[i2][j2] = true;
		
		int p = i2 * N + j2;
		int q;
		
		if ((i > 1) && isOpen(i-1,j)) //Above
		{
			q = (i2-1) * N + j2;
			if(!uf.connected(p, q))
				uf.union(p, q);
		}
		
		if ((i < N) && isOpen(i+1,j)) //Below only if not most bottom row
		{
			q = (i2+1) * N + j2;
			if(!uf.connected(p, q))
				uf.union(p, q);
		}
		
		
		if((j > 1) && isOpen(i,j-1)) //Left only if not most left
		{
			q = i2 * N + (j2-1);
			if(!uf.connected(p, q))
				uf.union(p, q);
		}

		
		if((j < N) && isOpen(i,j+1)) //Right only if not most right
		{
			q = i2 * N + (j2+1);
			if(!uf.connected(p, q))
				uf.union(p, q);
		}		
	}
	
	public boolean isOpen(int i, int j)
	{
		if (i < 0 || j < 0 || i > N || j > N ) throw new IndexOutOfBoundsException();
		
		return grid[i-1][j-1];	
	}
	
	public boolean isFull(int i, int j)
	{
		if (i < 0 || j < 0 || i > N || j > N ) throw new IndexOutOfBoundsException();
		
		boolean full = fullSite[i-1][j-1];
		
		if (isOpen(i, j) && !full) 
		{
			for (int k = 0; k < N; k++)
			{
				if (uf.connected(k, (i-1)*N+(j-1)))
				{
					fullSite[i-1][j-1] = true;
					break;
				}
			}
		}
		
		return fullSite[i-1][j-1];
	}
	
	public boolean percolates()
	{
		if (percolated) return percolated;
		
		for (int i = 0; i < N; i++)
			for (int j = count - N; j < count; j++)
				if(uf.connected(i, j)) percolated = true;
				
		return percolated;
	}
	
	public void fill()
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isFull(i+1,j+1);		
	}
	
	public boolean[][] getGrid() {
		return grid;
	}
	
	public boolean[][] getFullSiteMap() {
		return fullSite;
	}
	
	public int getN() {
		return N;
	}
	
	public int getOpenSiteCount() {
		return openSiteCount;
	}
	

	public static void main(String[] args)
	{
		
	}
	
	
}

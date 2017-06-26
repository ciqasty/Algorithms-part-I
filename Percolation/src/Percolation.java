import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean grid[][];
	private int size;
	private int opensites=0;
	private int top=0;
	private int bottom;
	private WeightedQuickUnionUF unionfind;
	//converts 2 dimensional row and col coordinates into 1 dimensional number
	private int returnIndex(int row,int col)
	{
		return (row-1)*size+col;
	}
	
	private void validate(int row,int col)
	{
		if (row<1||col<1||row>size||col>size)
		{
			throw new IndexOutOfBoundsException("The cell specified is out of bounds of this array, which is size " + size);
		}
	}
	//constructor
	public Percolation(int n)
	{
		if (n<=0) throw new IllegalArgumentException("N must be > 0");
		
		grid = new boolean[n][n];
		size=n;
		bottom=n*n+1;
		unionfind=new WeightedQuickUnionUF(size*size+2);
	}
	//open cell (change it's value to true) and merge with cells that are adjacent and open
	public void open(int row, int col)
	{
		//throw exception if row or col are out of bounds
		validate(row,col);
		int currentcell=returnIndex(row,col);
		//check if site open, if not then open it, increment counter
		if (!grid[row-1][col-1])
		{
			grid[row-1][col-1]=true;
			opensites++;
			
			//if row is the first row then connect to top
			if (row==1)
			{
				unionfind.union(top, currentcell);
			}
			
			//if row is the last row then connect to bottom
			if(row==size)
			{
				unionfind.union(currentcell, bottom);
			}
			
			//check adjacent cells, if open then union
			//check left
			if(col!=1 && isOpen(row,col-1)) unionfind.union(currentcell,returnIndex(row, col-1));
			//check right
			if(col!=size && isOpen(row,col+1)) unionfind.union(currentcell, returnIndex(row, col+1));
			//check above
			if(row!=1 && isOpen(row-1,col)) unionfind.union(currentcell, returnIndex(row-1,col));
			//check below
			if(row!=size && isOpen(row+1,col)) unionfind.union(currentcell, returnIndex(row+1,col));
		}
	}
	//return cell value
	public boolean isOpen(int row, int col)
	{
		validate(row,col);
		return grid[row-1][col-1];
	}
	//check if cell at given location is connected to top
	public boolean isFull(int row, int col)
	{
		validate(row,col);
		return unionfind.connected(top, returnIndex(row,col));
	}
	//return number of open sites
	public int numberOfOpenSites()
	{
		return opensites;
	}
	//check if grid percolates 
	public boolean percolates()
	{
		return unionfind.connected(top, bottom);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

package engine;

public class Labyrinth
{

	boolean debug = false; // diagnostics
	boolean demo = false; // show progressive filling

	public int[][] laby; // game area
	int[][] LOSmask; // line of sight mask
	int LOSbubbleSize = 2; // general extend of visibility to all directions

	public int ydim;
	public int xdim;

	public int[] playerCoords;

	public Labyrinth(int[][] laby, int[] plCoords)
	{
		this.laby = laby;
		ydim = laby.length - 1; // -1 as index start correction
		xdim = laby[0].length - 1;
		this.playerCoords = plCoords;
	}

	// show the thing (needs some beautification...)
	void display()
	{
		System.out.println();
		for(int[] yslice : this.laby)
		{
			for(int xcell : yslice)
			{
				System.out.print(xcell + "\t");
			}
			System.out.println("\n");
		}
	}

	// show maze WITH monster
	void display(Monster mon)
	{
		System.out.println();

		for(int y = 0; y < this.laby.length; y++)
		{
			for(int x = 0; x < this.laby[y].length; x++)
			{
				if(y == mon.getYcoord() && x == mon.getXcoord())
				{
					System.out.print("{M}\t");
					//System.out.println("match: monster= " + mon.coords[0]+mon.coords[1] + "yx=" +y+x);
				}
				else
					System.out.print(this.laby[y][x] + "\t");
			}
			System.out.println("\n");
		}
	}

	public void displayLOS()
	{
		System.out.println("\n\nLine of sight mask:");
		LOSmask = LineOfSight.makeLOS(this, playerCoords);
		LineOfSight.makeLOSbubble(this.LOSmask, playerCoords, LOSbubbleSize);
		LineOfSight.blurLOS(this.LOSmask);
		for(int[] slice : LOSmask)
		{
			for(int field : slice)
				System.out.print(field + "\t");
			System.out.println("\n");
		}
	}

	public int[][] returnLOS()
	{
		LOSmask = LineOfSight.makeLOS(this, playerCoords);
		LineOfSight.makeLOSbubble(this.LOSmask, playerCoords, LOSbubbleSize);
		LineOfSight.blurLOS(this.LOSmask);

		return LOSmask;
	}

	// getter;
	// return value of coordinate or relative neighbor
	// use getValue(coord, 0, 0) for same field
	int getValue(int[] coord, int y, int x)
	{
		return(laby[coord[0] + y][coord[1] + x]);
	}

	// setter;
	// set value of coordinates
	void setValue(int y, int x, int val)
	{
		laby[y][x] = val;
	}
	
	void setField(int[][] newField) {
		this.laby = newField;
	}

} // end class

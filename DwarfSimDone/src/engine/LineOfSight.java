package engine;

public class LineOfSight {

	
	// create "+" shaped lines of sight
	public static int[][] makeLOS(Labyrinth original, int[] coord) {
		
		int[][] emptyFields = new int[original.ydim + 1][original.xdim + 1];
		Labyrinth LOS = new Labyrinth(emptyFields,original.playerCoords);
		
		int bright = 2;
		//int dimmed = 1;
		
		
		int[] tmp = new int[2];
		tmp[0] = coord[0]; // clumsy copy of the coordinates, tmp will change, coord should stay
		tmp[1] = coord[1]; //
		int y = tmp[0];
		int x = tmp[1];
		
		while (y >= 0) {  // look up
			if (original.getValue(tmp, 0, 0) != 0) 
				break;
			LOS.laby[y][x] = bright;
			y--;tmp[0]--;
		}
		
		tmp[0] = coord[0]; // fetch original coordinates again
		tmp[1] = coord[1];
		y = tmp[0];
		x = tmp[1];

		while (x >= 0) {  // look left
			if (original.getValue(tmp, 0, 0) != 0) 
				break;
			LOS.laby[y][x] = bright;
			x--;tmp[1]--;
		}
		
		
		tmp[0] = coord[0];
		tmp[1] = coord[1];
		y = tmp[0];
		x = tmp[1];

		while (x <= original.xdim) {  // look right
			if (original.getValue(tmp, 0, 0) != 0) 
				break;
			LOS.laby[y][x] = bright;
			x++;tmp[1]++;
		}
		
		tmp[0] = coord[0];
		tmp[1] = coord[1];
		y = tmp[0];
		x = tmp[1];

		while (y <= original.ydim) {  // look down
			if (original.getValue(tmp, 0, 0) != 0) 
				break;
			LOS.laby[y][x] = bright;
			y++;tmp[0]++;
		}
		
		return LOS.laby;
	} // end method

	
	// create illuminated area around (player-)coordinates, regardless of walls 
	public static void makeLOSbubble(int[][] orig, int[] coord, int bubbleSize) {
		int cy = coord[0];
		int cx = coord[1];
		int bright = 2;
		for (int y = -bubbleSize; y<=bubbleSize ;y++) {
			for (int x = -bubbleSize; x<=bubbleSize ;x++) {
				try {orig[cy+y][cx+x] = bright;}
				catch (Exception e) {} // just for out of bound issues, handling not necessary
			}
		}
	}

	// make neighbors of bright (2) cells dim (1)
	public static void blurLOS(int[][] orig) {
	
		int ydim = orig.length;
		int xdim = orig[0].length;
		int bright = 2; // code for full illumination
		
		for (int y = 0; y < ydim ;y++) {
			for (int x = 0; x < xdim; x++) {
				if (orig[y][x] == bright) LineOfSight.blurHelper(orig,y,x);
			}
		}
	} // end blurLOS
	
	public static void blurHelper(int[][] orig, int y, int x) {
		int dimmed = 1; // code for slight illumination
		try { if (orig[y-1][x] == 0) orig[y-1][x]  = dimmed;} catch (Exception e){}
		try { if (orig[y+1][x] == 0) orig[y+1][x]  = dimmed;} catch (Exception e){}
		try { if (orig[y][x-1] == 0) orig[y][x-1]  = dimmed;} catch (Exception e){}
		try { if (orig[y][x+1] == 0) orig[y][x+1]  = dimmed;} catch (Exception e){}
	} // end blurHelper
	
} // end class

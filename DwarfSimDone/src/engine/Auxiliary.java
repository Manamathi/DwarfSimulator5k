package engine;

// collects unrelated convenience methods
public class Auxiliary {
		
static int[][] copyField(int[][] orig) {
	
	int ydim = orig.length;
	int xdim = orig[0].length;
	
	int[][] out = new int[ydim][xdim];
	
	for (int y = 0; y < ydim ;y++) {
		for (int x = 0; x < xdim; x++) {
			out[y][x] = orig[y][x];
		}
	}
	return out;
}


} // end class
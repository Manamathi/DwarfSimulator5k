package engine;
import java.util.LinkedList;


public class Backtrack {

	
static char[] trace(Labyrinth myLab, int[] end) {
	
	//boolean report = true;
	boolean debug = false;
	
	int[] coord = end; // algorithm starts here
	if(debug) {
		myLab.display();
		System.out.println("end coord given are " + coord[0] + coord[1]);
	}
	
	LinkedList<Object> navi = new LinkedList<Object>();
	int[] shift = {0,0}; // where to move active field
 
while (true) {

    char way = 'E'; // dummy value, stays E for 'Exit' unless the backtracking continues
    int val = myLab.laby[coord[0]][coord[1]];   // current coords to check
    
    
    //check up
    if (coord[0] > 0){ // in bounds?
    	if (val > myLab.getValue(coord,-1,0)) {  // compare my value with upper field
	        val = myLab.getValue(coord,-1,0);    // if upper field is smaller, go up
	        way = 'U';
	        shift[0] = -1;
	        shift[1] = 0;
    	}
    }
    //check down
    if (coord[0] < myLab.ydim){
        if (val > myLab.getValue(coord,1,0)) {
            val = myLab.getValue(coord,1,0);
            way = 'D';
            shift[0] = 1;
            shift[1] = 0;
        }
    }
    //check left
    if (coord[1] > 0) {
        if (val > myLab.getValue(coord,0,-1)) {
            val = myLab.getValue(coord,0,-1);
            way = 'L';
            shift[0] = 0;
            shift[1] = -1;
        }
    }            		  		
    //check right
    if (coord[1] < myLab.xdim){
        if (val > myLab.getValue(coord,0,1)){
            val = myLab.getValue(coord,0,1);
            way = 'R';
            shift[0] = 0;
            shift[1] = 1;
        }
    }
    
    if(debug) System.out.println(way);
    
    if (myLab.getValue(coord, 0, 0) == 1)	break;   // arrived at last field = 1
    
    
    navi.add(way);
    
    
    coord[0] = coord[0] + shift[0]; // move current coordinates to lowest neighbor
    coord[1] = coord[1] + shift[1]; // move current coordinates to lowest neighbor
                   //return (way)
} // end while

if (debug) myLab.display();

char[] solution = new char[navi.size()]; // make char array with appropriate length
if (debug) System.out.println("length of solution = " + navi.size());


for (int i = 0; i < solution.length; i++) { // 
	solution[i] = (char)navi.pop();
}

return solution;
} // end trace
} // end class

package engine;

import java.util.LinkedList;
import java.util.Scanner;

/*@formatter:off */
public class NaviFlood {

	Scanner keyboard = new Scanner(System.in);
	Labyrinth myLab;
	int[] coord = new int[2];
	int[] start = new int[2];
	int[] end   = new int[2];

	public NaviFlood(Labyrinth original, int[] start, int[] end){
		//this.myLab = new Labyrinth(original.laby);
		//this.myLab.laby = Aux.copyField(original.laby); // explained in aux class
//		this.myLab = new Labyrinth(original.laby);
		this.coord[0]=start[0];
		this.coord[1]=start[1];
		this.start[0]=start[0];
		this.start[1]=start[1];
		this.end[0]=end[0];
		this.end[1]=end[1];
		
		this.myLab = new Labyrinth(original.laby, start);
		this.myLab.laby = Auxiliary.copyField(original.laby);

	}

	// current coordinates
	int counter = 2; // distance to start (for labyrinth solver)

	LinkedList<int[]> nextCoordSet = new LinkedList<int[]>();


	// flood-like filling of labyrinth
	void sploosh(int y, int x){
	    if (myLab.getValue(coord, y, x) == 0) { //check if field is empty
	    	int[] tmp = {coord[0]+y, coord[1]+x};
	        nextCoordSet.add(tmp);
	        myLab.setValue( (coord[0]+y), (coord[1]+x), counter);
	        	if (myLab.debug) {
	        					System.out.println("added " + tmp[0] + tmp[1] + " to next coordSet\n"
	        							+ "\tnew coordSet length: " + nextCoordSet.size());

	        					System.out.println("set field " + (coord[0]+y) + (coord[1]+x) + " to " + counter);
	        	}
	    }
	}


	public char[] solve() {

		myLab.setValue(start[0],start[1],1); 	   // entry point set to 1

		// list of all the new fields to check in the next iteration
		// initialized with the starting position
		LinkedList<LinkedList<int[]>> nextFields = new LinkedList<LinkedList<int[]>>();  // o. m. g.
		LinkedList<int[]> coordSet = new LinkedList<int[]>();
		coordSet.add(start);
		nextFields.add(coordSet);


		// the main loop to fill the labyrinth
		for (int i = 0; i<(myLab.ydim*myLab.xdim); i++) {

		    if (i == (myLab.ydim*myLab.xdim-1)){ 			// give up after threshold
		        System.out.println("Can't solve :'(");
		        break;
		    }

		    if (myLab.debug) System.out.println("counter: " + counter + ". Number of coordinates to check in next iteration: " + nextCoordSet.size());
		    if (nextFields.size()==0) {
		    	System.out.println("i kinda ran out of fields to check");
		    	break;
		    }

		    coordSet = nextFields.pop();

		    nextCoordSet = new LinkedList<int[]>();   // refresh for each iteration

		    while (coordSet.size() > 0) {

		    	if (myLab.debug) System.out.println("coordSetsize=" + coordSet.size());
		        coord = coordSet.pop();
		        if (myLab.debug) System.out.println("--- current coords are " + coord[0] + coord[1]);

		        // check if neighbors of cell are lower than cell value
		        if (coord[0] > 0) sploosh(-1,0);     //check up
		        if (coord[0] < myLab.ydim) sploosh(1,0);   //check down
		        if (coord[1] > 0) sploosh(0,-1);     //check left
		        if (coord[1] < myLab.xdim) sploosh(0,+1);  //check right
		        if (coord[0] == end[0] && coord[1] == end[1]) break;  // exits WHILE loop 1 step after filling the goal's neigbors

		    } // current set of coordinates done; while loop complete

		    if (coord[0] == end[0] && coord[1] == end[1]){
		        if (myLab.demo) {
		        	System.out.println("\n\n!!!GOAL REACHED !!!\n Shortest distance is " + (counter-1));
		        	myLab.display();
		        }
		        break; // exit FOR loop
		    }


		    nextFields.add(nextCoordSet);
		    if (myLab.debug) System.out.println("i added a set of " + nextCoordSet.size() + " new coordinates for the next iteration! ");

		    counter = counter + 1;
		    //print "remaining fields:" + str(nextFields)

		    if (myLab.demo) {
		    	myLab.display();
		    	System.out.println("Press enter to continue...");
		    	keyboard.nextLine();
		    }




		} // end for-loop (filling)

		return Backtrack.trace(myLab, end); // fed with filled maze and "end" coordinates
	} // end solve()
}

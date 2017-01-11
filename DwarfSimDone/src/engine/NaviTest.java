package engine;

/*@formatter:off */
public class NaviTest {

	public static void main(String[] args) {

		int[][] field = new int[][]{
			//   0   1   2   3   4   5   6	 7   8
			  { 00, 00, 00, 99, 00, 00, 00, 99, 99,},  // 0
			  { 00, 99, 00, 99, 00, 99, 00, 00, 00,},  // 1
			  { 00, 00, 00, 99, 00, 00, 00, 99, 99,},  // 2
			  { 99, 00, 99, 99, 00, 99, 00, 99, 00,},  // 3
			  { 00, 00, 00, 99, 00, 99, 00, 99, 00,},  // 4
			  { 00, 99, 00, 00, 00, 99, 00, 00, 00,},  // 5
			  { 00, 00, 00, 99, 00, 99, 00, 99, 00,},  // 6
			  { 00, 99, 00, 00, 00, 99, 00, 99, 00,}   // 7
			};
		int[] start = {0,0};
		int[] end   = {7,8};
		char[] solution;


//		Labyrinth laby = new Labyrinth(field);
		Labyrinth laby = new Labyrinth(field, start);
		//laby.display();
		laby.displayLOS();

		NaviFlood solver = new NaviFlood(laby, end, start); // end and start switched (here, only!), because this algorithm actually traces backwards!
		solution = solver.solve();

		laby.display();
		System.out.print("solution: ");
		for (char dir : solution) System.out.print(dir + " ");


		int[] mCoords = {0,0};
		//Monster mon = new Monster(mCoords);
		//mon.setPath(solution);

//		int[] monsterRandomCoords = {4,0};
//		MonsterRandom mon = new MonsterRandom(monsterRandomCoords, laby);



		//laby.display(mon);
		System.out.println("\nPress enter to start demo...");
		solver.keyboard.nextLine();

//		Thread heartbeat = new Thread(){
//			@Override
//			public void run() {
//				//String dot = ".";
//				while (true) {
//					//System.out.print(dot);
//					//dot = dot + ".";
//					mon.move();
//					//System.out.println("monster at " + mon.coords[0]+mon.coords[1] + "\n");
//					laby.display(mon);
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		heartbeat.start();
	}
}

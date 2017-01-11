
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

import engine.Labyrinth;
import engine.MapLoader;
import engine.Monster;
import engine.MonsterRandom;
import engine.NaviFlood;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/*@formatter:off */
public class _03_1_SpielFeld extends Canvas
{

	public int[] playCo = 	 {5,4}; // Player  Coordinates,  Y,X
	int[] seekerCo = {11,16}; // Monster Coordinates,  Y,X
	int[] goblinCo = {0,0};//{5,11}; // Monster Coordinates,  Y,X
	int[] orcCo =    {14,0}; // Monster Coordinates,  Y,X
	int[] exit = 	 {14,16};
	
	int lvl = 1;
	public int life = 3;
	boolean gameOver = false;
	boolean finish = false;
	
	LinkedList<String> maps = new LinkedList<String>(Arrays.asList("Maps\\Map1.txt","Maps\\Map3.txt"));
	int[][] field = loadMap(maps.pop());
	Labyrinth level = new Labyrinth(field, playCo);
	 
	NaviFlood solver = new NaviFlood(level, playCo, seekerCo); // end and start switched (here, only!), because this algorithm actually traces backwards
	char[] solution = solver.solve();
	
	
	Monster		  seekerMon    = new Monster(seekerCo, this);
	MonsterRandom goblinMon    = new MonsterRandom(goblinCo, level, this);
	MonsterRandom orcMon       = new MonsterRandom(orcCo, level, this);

	int[][] LOSmask;
	
	Thread heartbeat = new Thread(){
		@Override
		public void run() {
			while (true) {
				
				if (life < 1) {
					setThreadPassive();
					System.out.println("GAME OVER!");
					gameOver = true;
					paint();
					return;
				}
				
				if (threadActive) {
					checkExit();
					seekerMon.move();
					//System.out.println("goblin before " + goblinMon.getYcoord() + " " + goblinMon.getXcoord() + "\n");
					goblinMon.move();
					//System.out.println("goblin now" + goblinMon.getYcoord() + " " + goblinMon.getXcoord() + "\n");

					orcMon.move();
					paint();
				   }					//System.out.println("monstercoordiantes are X " + seekerMon.getXcoord() + " Y " + seekerMon.getYcoord());
					
					try {
						Thread.sleep(600); } catch (InterruptedException e) {e.printStackTrace();}
			}
		}


	};

	boolean threadActive = false;

	

	public _03_1_SpielFeld() throws FileNotFoundException {
		setWidth(900);
		setHeight(700);
		setStyle("-fx-background-color: white;");
		paint();
		
		seekerMon.setPath(solution);
		heartbeat.start();
		System.out.println("Dwarf On!!\n");
	}
	



	 public int[][] loadMap(String fileName) throws FileNotFoundException {
		MapLoader mapLoader = new MapLoader();
		FileInputStream fileInputStream = new FileInputStream(fileName);
		return mapLoader.readTextFileToArray(fileInputStream);
	 }
	 
	public void setThreadPassive() {
		threadActive = false;		
	}

	
 
	 // Event handling from _GameView ///////////////////////////////////////////////////////
	public void moveUP() {
		if ( (playCo[0] > 0) && (level.laby[playCo[0]-1][playCo[1]] == 0) )
			{
			if (isOccupied(-1,0)) return;
			else {
				playCo[0]--;
				reroute();
				threadActive = true; 
				paint();
				}
			}
		}
	public void moveDN() {
		if ( (playCo[0] < level.ydim) && (level.laby[playCo[0]+1][playCo[1]] == 0) ) {
			if (isOccupied(1,0)) return;
			else {
			playCo[0]++;				reroute(); 		threadActive = true; 	 paint();	 }	} }
	public void moveLE() {
		if ( (playCo[1] > 0) && (level.laby[playCo[0]][playCo[1]-1] == 0) ) {
			if (isOccupied(0,-1)) return;
			else {
			playCo[1]--;				reroute(); 		threadActive = true; 	 paint();	 }	} }
	public void moveRI() {
		if ( (playCo[1] < level.xdim) && (level.laby[playCo[0]][playCo[1]+1] == 0) ) {
			if (isOccupied(0,1)) return;
			else {
			playCo[1]++;				reroute(); 		threadActive = true; 	 paint();	 }	} }

	public boolean isOccupied(int yshift, int xshift) {
		//System.out.println("placo " + playCo[0]+ " " + playCo[1]);		//System.out.println("goblin " + goblinMon.getYcoord() + " " + goblinMon.getXcoord() + "\n");
		if ( ((playCo[0]+yshift) == goblinMon.getYcoord()) && ((playCo[1]+xshift) == goblinMon.getXcoord())) {
			return true;}
		else if ( ((playCo[0]+yshift) == orcMon.getYcoord()) && ((playCo[1]+xshift) == orcMon.getXcoord())) {
			return true;}
		else if ( ((playCo[0]+yshift) == seekerMon.getYcoord()) && ((playCo[1]+xshift) == seekerMon.getXcoord())) {
			return true;}
		else return false;	
	}
	
	public void reroute() {
		NaviFlood solver = new NaviFlood(level, playCo, seekerCo); // end and start switched (here, only!), because this algorithm actually traces backwards
		char[] solution = solver.solve();
		seekerMon.setIndex(0);
		seekerMon.setPath(solution);
	}

	private void checkExit() {
		if ( (lvl == 2) && (playCo[1]>=8) && (playCo[1]<=11) && (playCo[0]>=3) && (playCo[0]<=4) )
		{
			finish = true;
			setThreadPassive();
			paint();
			return;
		}
		if (playCo[0]==exit[0] && playCo[1]==exit[1]) {
			System.out.println("Exit found!");
			try {
				level.laby = loadMap(maps.pop());
				lvl++;
				exit[0]=0; // remove exit
				exit[1]=0;
				playCo[0]--;
				goblinMon.coords[0]=13;
				goblinMon.coords[1]=1;
				orcMon.coords[0]=13;
				orcMon.coords[1]=18;
				seekerMon.coords[0]=1;
				seekerMon.coords[1]=1;
				reroute();
				
				
				// TODO: koordinaten von monstern und objekten aktualisieren

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}			
	}
	
	
// Loading images: //////////////////////////////////////////////////////////////////////
  // objects
	Image dwarf = 	new Image(getClass().getResourceAsStream("dwarf2.png"));
	//Image dwarf = 	new Image(getClass().getResourceAsStream("cadence.png"));
	Image stairs = 	new Image(getClass().getResourceAsStream("stairs.png"));
	Image lisaMon = new Image(getClass().getResourceAsStream("lisaMon2.png"));
  //Image lisaMon = new Image(getClass().getResourceAsStream("cthul2.png"));
	Image eye = 	new Image(getClass().getResourceAsStream("eyeGlow.png"));
	Image goblin = 	new Image(getClass().getResourceAsStream("goblin2.png"));
	Image orc = 	new Image(getClass().getResourceAsStream("orc.png"));

  // tiles
	Image floor = 		new Image(getClass().getResourceAsStream("floor.png"));
	Image floorDark = 	new Image(getClass().getResourceAsStream("floorDark.png"));
	Image wall = 		new Image(getClass().getResourceAsStream("exwall2.png"));
	Image wallAlt = 	new Image(getClass().getResourceAsStream("exwallAlt.png"));
	Image wallDark = 	new Image(getClass().getResourceAsStream("exwallDark.png"));
	
  // stuff
	Image heart = 		new Image(getClass().getResourceAsStream("heart.png"));
	Image treasure = 	new Image(getClass().getResourceAsStream("treasure.png"));
    Image view =		new Image(getClass().getResourceAsStream("fog4.png"));
    Image darkness =	new Image(getClass().getResourceAsStream("fog10.png"));
	Image gameOverPic = new Image(getClass().getResourceAsStream("gameover.png"));
	Image finishPic =   new Image(getClass().getResourceAsStream("finish.png"));
	
//               \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
// Paint Method: //////////////////////////////////////////////////////////////////////
	public void paint()	{
		GraphicsContext gc = getGraphicsContext2D();
		//paintWorld(gc);
		
		if (finish == true) {
			System.out.println("Drawing GameOver...");
			gc.drawImage(finishPic, 	0*40+0+0,	9*40+0+0, 900,200);
			return;
		}
		
		if (gameOver == true) {
			System.out.println("Drawing GameOver...");
			gc.drawImage(gameOverPic, 	0*40+0+0,	0*40+0+0, 900,700);
			return;
		}

		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(0.0);
				gc.strokeRect(i * 40 + 20 + i, j * 40 + 20 + j, 40, 40);
			}
		}
	// VISIBLE AREA
		LOSmask = level.returnLOS();

	// Draw Floor;
        for (int i = 0; i<20;i++)
        {
            for (int j = 0; j<15;j++)
            {
            	if ( (level.laby[j][i] == 0) && (LOSmask[j][i] != 0) )gc.drawImage(floor, i*40+20+i, j*40+20+j,41,41);
            	else if (level.laby[j][i] == 0)// && (LOSmask[j][i] == 1) )
           			gc.drawImage(floorDark, i*40+20+i, j*40+20+j,41,41);
            }
        }

      // floor elements
      //  if (LOSmask[exit[1]][exit[0]] != 0)
        	gc.drawImage(stairs,	exit[1]*40+20+exit[1], 	exit[0]*40+20+exit[0],  40,40);
        	if (lvl == 2)
        		gc.drawImage(treasure,	8*40+20+8, 	3*40+20+3,  3*40,2*40);
        	
   		         //  x:             x*40(koord) + 20(rand) + koordKorrektur // drawsize  // y (...)

        // Player						
        gc.drawImage(dwarf, 	playCo[1]*40+15+playCo[1],	playCo[0]*40+15+playCo[0],    50,50);

        // Monster
//        gc.drawImage(lisaMon,  mon1Co[0]*40+20+mon1Co[0], mon1Co[1]*40+20+mon1Co[1],	40,40);
        
    	if ( LOSmask[seekerMon.getYcoord()][seekerMon.getXcoord()] != 0) 
    		gc.drawImage(lisaMon,  seekerMon.getXcoord()*40+20+seekerMon.getXcoord(), seekerMon.getYcoord()*40+20+seekerMon.getYcoord(),	40,40);
        gc.drawImage(goblin, 	goblinMon.getXcoord()*40+20+goblinMon.getXcoord(), goblinMon.getYcoord()*40+20+goblinMon.getYcoord(),	40,40);
        gc.drawImage(orc, 		orcMon.getXcoord()*40+20+orcMon.getXcoord(), orcMon.getYcoord()*40+20+orcMon.getYcoord(),	35,35);


        // walls
        for (int i = 0; i<20;i++){
            for (int j = 0; j<15;j++){
            	if ( (level.laby[j][i] != 0) && (LOSmask[j][i] != 0) )
            		if ((j+i+i) % 7 == 0)
            			 gc.drawImage(wallAlt, i*40+10+i, j*40+10+j,51,52);
            		else gc.drawImage(wall, i*40+10+i, j*40+10+j,51,52);
            	else if ( level.laby[j][i] != 0) //&& (LOSmask[j][i] == 1) )
            		gc.drawImage(wallDark, i*40+10+i, j*40+10+j,51,52);
            }
        }
        
      // light Bubble & the inner darkness of the dwarven condition
        if (lvl == 2)
        	gc.drawImage(darkness, 	playCo[1]*40+15+playCo[1]-900,	playCo[0]*40+15+playCo[0]-700, 1840,1440);
        else
        	gc.drawImage(view, 	playCo[1]*40+15+playCo[1]-900,	playCo[0]*40+15+playCo[0]-700, 1840,1440);
		
      // seekerMon's creepy eye 
        gc.drawImage(eye,  seekerMon.getXcoord()*40+28+seekerMon.getXcoord(), seekerMon.getYcoord()*40+16+seekerMon.getYcoord(),	24,24);

       // life bar
        for (int h = 1; h <= life; h++)
        	gc.drawImage(heart,  h*40, 15*40+36,	20,20);

        Image paused = new Image(getClass().getResourceAsStream("paused.png"));
        if (!threadActive) gc.drawImage(paused, 	8*50+15+8,	4*40+15+4,    250,50);
	} // end paint()




	public void reportCo() {
		System.out.println("placo " + playCo[0]+ " " + playCo[1]);
		// TODO Auto-generated method stub
	}



} // end class



// for debug:
//int[][] field = new int[][]{
////   0   1   2   3   4   5   6	 7   8	 9  10  11  12  13  14  15  16  17  18  19
// { 99, 99, 00, 99, 00, 00, 00, 99, 99, 00, 00, 00, 99, 00, 00, 00, 99, 99, 99, 99},  // 0
// { 00, 99, 00, 99, 00, 99, 00, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 1
// { 00, 99, 00, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00, 99, 00, 00, 00, 00},  // 2
// { 00, 00, 00, 00, 00, 00, 00, 99, 00, 00, 99, 00, 00, 00, 00, 99, 99, 00, 00, 99},  // 3
// { 99, 00, 99, 99, 00, 00, 00, 99, 00, 00, 00, 00, 99, 00, 00, 00, 99, 99, 00, 99},  // 4
// { 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00},  // 5
// { 00, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99, 99, 00, 99, 99, 00, 00, 00, 99},  // 6
// { 99, 99, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 7
// { 00, 00, 99, 00, 00, 00, 99, 99, 99, 99, 99, 00, 00, 00, 00, 99, 00, 99, 00, 00},  // 8
// { 00, 99, 99, 99, 00, 99, 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99},  // 9
// { 00, 00, 00, 99, 00, 99, 00, 99, 99, 00, 00, 00, 99, 00, 00, 99, 00, 00, 00, 00},  // 10
// { 99, 99, 99, 99, 00, 00, 00, 99, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00},  // 11
// { 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 00, 99, 00, 99},  // 12
// { 00, 99, 00, 00, 00, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99, 00, 99},  // 13
// { 00, 99, 00, 00, 00, 99, 00, 99, 00, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99}   // 14
//};
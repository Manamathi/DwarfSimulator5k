package engine;

import application._03_1_SpielFeld;

public class Monster {

	char[] path;
	public int[] coords;
	int index = 0;
	_03_1_SpielFeld gameArea;
	
	public Monster(int[] coords, _03_1_SpielFeld gameArea) {
		this.coords = coords;
		this.gameArea = gameArea;
		System.out.println("Good day to you. The coordinates I was given are: X" + coords[0] + " Y" + coords[1] + ". I'm going to eat the dwarf now.");
	}
	
	public void setPath(char[] newPath) {
		this.path = newPath;
	}
	
	public int getYcoord() {
		return coords[0];
	}
	public int getXcoord() {
		return coords[1];
	}
	
	
	public void move() {
		
		if (index > path.length-1) {
			gameArea.life--;
			System.out.println("SeekerMon: \"I think I'm done. Where the hell is he? Did I eat him already? I think I ate him. Oh well.\"");
			return;
		}

		int direction = path[index];
		index++;
		
		if (direction == 'U')
			coords[0]--;
		if (direction == 'D')
			coords[0]++;
		if (direction == 'L')
			coords[1]--;
		if (direction == 'R')
			coords[1]++;
	}

	public void setIndex(int i) {
		index = i;		
	}
	
}

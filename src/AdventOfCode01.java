/* Tara Moses
 * August 24, 2018
 * Advent Of Code 2016 #1: Easter Bunny HQ
 */
 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

enum LR {
	Left("L"), Right("R");
	private String value;
	
	private LR(String str) {
		value = str;
	}	
	
	String value() { return value; }
}

class Instruction {
	
	LR Direction;
	int Distance;
	
	Instruction(String input) {
		Direction = (input.charAt(0) == 'L') ? LR.Left : LR.Right;
		Distance = Integer.parseInt(input.substring(1,input.length()));
	}
	
	String toStr() {
		return Direction.value() + Integer.toString(Distance);
	}
}

public class AdventOfCode01 {
	public static void main(String[] args) {
		
		Scanner scan;
		
		// Try to open the file		
		try {
			scan = new Scanner(new File("../inputs/input01.txt"));
		} catch (Exception ex) {
			System.out.println("ERROR: File input01.txt not found. \n" + ex);
			return;
		}
		
		// Parse		
		
		int facing = 0; // 0 North, 1 West, etc.			
		int nMinusS = 0;
		int eMinusW = 0;
		
		while (scan.hasNext()) {
			Instruction x = new Instruction(scan.next().replace(",",""));
			
			// Figure out what direction you're facing
			if (x.Direction == LR.Left) {
				facing = (facing + 3) % 4;
			} else {
				facing = (facing + 1) % 4;				
			}
			
			// We need the abs val of N-S and the abs value of E-W
			if (facing == 0) 
				nMinusS += x.Distance;
			else if (facing == 2) 
				nMinusS -= x.Distance;
			else if (facing == 1) 
				eMinusW += x.Distance;
			else if (facing == 3) 
				eMinusW -= x.Distance;
		}
		
		int finalAns = Math.abs(nMinusS) + Math.abs(eMinusW);
		System.out.println("Final answer: " + finalAns);
	}
}









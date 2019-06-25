package seaport;

import java.util.Scanner;

/**
 * File: Thing.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This is the super class that implemets interface Comparable
 */
public class Thing implements Comparable <Thing> {
	/**
	 * String for name
	 */
	String name;
	/**
	 * int for index
	 */
	int index;
	/**
	 * int for parent type
	 */
	int parent;
	/**
	 * No-argument constructor
	 */
	public Thing(){}
	/**
	 * Constructor that takes argument from input file through Scanner
	 * @param sc is the scanner from input file
	 */
	public Thing(Scanner sc){
		if (sc.hasNext()) name = sc.next();
		if (sc.hasNextInt()) index = sc.nextInt();
		if (sc.hasNextInt()) parent = sc.nextInt();
		
		
	}
   
/**
 * this method prints the name and index of thing
 */
	public String toString(){
		String st =   name + " " + index  ;
		return st;
		
	}
	
	
	@Override
	public int compareTo(Thing o) {
		// will add here in project 2,3 or 4
		return 0;
	}

}

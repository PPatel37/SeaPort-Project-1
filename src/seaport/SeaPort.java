package seaport;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * File: SeaPort.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This class is subclass that extends super class Thing. this class represents Seaport
 */
public class SeaPort extends Thing {
	
	/**
	 * list of docks on SeaPort
	 */
	ArrayList<Dock> docks = new ArrayList<>(); 
	/**
	 * list of ships in a que
	 */
	ArrayList<Ship> que = new ArrayList<>(); 
	/**
	 * list of ships present on port
	 */
	ArrayList<Ship> ships = new ArrayList<>(); 
	/**
	 * list of person working on port
	 */
	ArrayList<Person> persons = new ArrayList<>(); 
	/**
	 * Constructer to create an object of class SeaPort
	 * @param sc is a Scanner that read data from input file
	 */
	public SeaPort(Scanner sc){
	  super(sc);
		
	}

	
	@Override
	public String toString () {
		 String st = "\n\n\n>>>>> The world: \n\nSeaPort: " + super.toString();
		 for (Dock md: docks) st += "\n" + md;
		 st += "\n\n --- List of all ships in que:";
		 for (Ship ms: que ) st += "\n > " + ms;
		 st += "\n\n --- List of all ships:";
		 for (Ship ms: ships) st += "\n > " + ms;
		 st += "\n\n --- List of all persons:";
		 for (Person mp: persons) st += "\n > " + mp;
		 return st;
		 } // end method toString
	
	
}

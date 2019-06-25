package seaport;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * File: World.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This class represent the world that has one or more SeaPort. This is the subclass of Thing
 */
public class World extends Thing {
/**
 * list of SeaPorts
 */
	ArrayList<SeaPort> ports = new ArrayList<>();
	/**
	 * represents time
	 */
	PortTime time;
	/**
	 * this is the list that holds search things
	 */
	ArrayList<Thing> searchList ;

	/**
	 * no-argumet constructor
	 */
	public World(){}
	
	
	
/**
 *  Handling a line from the file
 * @param st is a line from file 
 */
	public void process (String st) {
	// System.out.println ("Processing >" + st + "<");
		 Scanner sc = new Scanner(st);
		 if (!sc.hasNext()){
			 sc.close();
		     return;}
		 switch (sc.next()) {
		 case "port" : 
			 addPort (sc);
		     break;
		 case "dock":
			addDock(sc);
			 break;
		 case "pship":
			 addPship(sc);
			 break;
		 case "cship":
			 addCship(sc);
			 break;
		 case "person":
			 addPerson(sc);
			 break;

		 }
		 }//end of process
	 

	/**
	 * Adding port to list 
	 * @param sc is a scanner that reads input file
	 */
   public void addPort(Scanner sc) {
	SeaPort port = new SeaPort(sc);
	ports.add(port);
}
 

	/**
	 * Adding dock to list 
	 * @param sc is a scanner that reads input file
	 */
   public void addDock(Scanner sc){
		 Dock dock = new Dock(sc);
		 getSeaPortByIndex(dock.parent).docks.add(dock);	 
	}

	/**
	 * Adding passenger ship to list 
	 * @param sc is a scanner that reads input file
	 */
   public void addPship(Scanner sc){
	   Ship pShip = new PassengerShip(sc);
	   assignShip(pShip);

   }

	/**
	 * Adding cargo ship to list 
	 * @param sc is a scanner that reads input file
	 */
   public void addCship(Scanner sc){
	   Ship cShip = new CargoShip(sc);
	  assignShip(cShip);
   }

	/**
	 * Adding person to list 
	 * @param sc is a scanner that reads input file
	 */
   public void addPerson(Scanner sc){
	   Person p = new Person(sc);
	   getSeaPortByIndex(p.parent).persons.add(p);
   }
   /**
    * adding jobs to ship
    * @param sc
    */
    public void addJobs(Scanner sc){
    	Job j = new Job(sc);
    	System.out.println(j.toString());
    	System.out.println(j.parent);
    	getShipByIndex(j.parent).jobs.add(j);
    	
    	for(SeaPort port : ports)
    		for(Ship s: port.ships)
    			for(Job job: s.jobs)
    				System.out.println(job.toString());
    			
    }
   
/**
 * get port by index
 * @param x is a parent
 * @return A seaport whose index matche parent, null otherwise
 */
   public SeaPort getSeaPortByIndex (int x){
		 for (SeaPort port: ports)
			 if (port.index == x)
				 return port;
		         return null;
	 }
   /**
    * get dock by index
    * @param parent is a parent
    * @return A dock whose index matches to parent, null otherwise
    */
   public Dock getDockByIndex(int parent) {
	   for(SeaPort port: ports)
	     for(Dock dock: port.docks)
	    	 if(dock.index == parent)
	    		 return dock;
	    		 
	     return null;
}
   
   
 /**
  * Linking a ship to its parent
  * @param ms is a ship
  */
  public void assignShip (Ship ms) {
 		 Dock md = getDockByIndex (ms.parent);
 		 if (md == null) {
 		 getSeaPortByIndex(ms.parent).ships.add (ms);
 		 getSeaPortByIndex(ms.parent).que.add (ms);
 		 return;
 		 }
 		 md.ship = ms;
 		 getSeaPortByIndex(md.parent).ships.add(ms);
 		 } // end method assignShip

/**
 *  Finding a ship by index - finding the parent of a job, for example:
 * @param x is a parent of a job
 * @return a ship whose index matches jobs parent , null otherwise
 */
 public  Ship getShipByIndex (int x) {
	 System.out.println("Flag 1");
		 for (SeaPort msp: ports)
		 for (Ship ms: msp.ships)
		      if (ms.index == x)
		           return ms;
		 return null;
		 } // end getDockByIndex	
  
	
/**
 * performs search	 
 * @param type is a type of search user perform
 * @param target is a input from textfield
 */
public void doSearch(String type,String target)	{
	searchList = new ArrayList<>();
	switch(type){
	case "Name":
		
		for(SeaPort port: ports)
			searchName(port,target);
		
		break;
		
	case "Index":
		for(SeaPort port: ports)
			searchIndex(port,target);
		break;
	case "Skills":
		for(SeaPort port: ports)
		for(Person p: port.persons)
			if(p.skill.equalsIgnoreCase(target)){
				searchList.add(p);
			}
		break;
	}
}


/**
 * method that search for name in each port for target		
 * @param port is a Seaport
 * @param target is a input entered by user
 * @return return a search list of things that maches with input target
 */
public ArrayList<Thing> searchName(SeaPort port, String target){
	
	if (port.name.equalsIgnoreCase(target)){
		searchList.add(port);
		
	}
	for(Dock dock: port.docks)
		if(dock.name.equalsIgnoreCase(target))
			searchList.add(dock);
	for(Ship ship: port.ships )
		if(ship.name.equalsIgnoreCase(target))
			searchList.add(ship);
	for(Person p: port.persons)
		if(p.name.equalsIgnoreCase(target))
			searchList.add(p);
	
	return searchList;
}
/**
 * method that search for index in each port for target		
 * @param port is a Seaport
 * @param target is a input entered by user
 * @return return a search list of things that maches with input target
 */
public ArrayList<Thing> searchIndex(SeaPort port, String target){
	int x = Integer.parseInt(target);
		if (port.index == x){
		searchList.add(port);
	}
	for(Dock dock: port.docks)
		if(dock.index == x)
			searchList.add(dock);
	for(Ship ship: port.ships )
		if(ship.index == x)
			searchList.add(ship);
	for(Person p: port.persons)
		if(p.index == x)
			searchList.add(p);
	
	return searchList;
}
		


}

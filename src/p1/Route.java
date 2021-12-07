package p1;

import java.util.*;


/* 
 * Route class is a parent class to flight
 */

class Route {
	
	// variables to store flight info
	int id;
	
	String source;
	String destination;
	
	// constructor for Route object
	Route(String from,String to)
	{
		source=from;
		destination=to;
	}
}




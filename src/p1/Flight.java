package p1;

import java.util.*;



/*
 *  Flight class that extends the Route class
 */


public class Flight extends Route { 
	
	// variables to store flight information
	int id,seat_eco,seat_bus; 
	
    String company;
    String departure_time;
    String arrival_time;
    String source;
    String destination;
    
	
	float price_eco;
	float price_bus;
	
	
	// Constructor for the Flight object
	Flight(int i,String c,String source,String destination,String start,String end,int eco,int bus,float eco_price,float bus_price) {
		
		
		super(source,destination);
		
		id=i;
		company=c;
		departure_time=start;
		arrival_time=end;
		seat_eco=eco;
		seat_bus=bus;
		price_eco=eco_price;
		price_bus=bus_price;
	}
}

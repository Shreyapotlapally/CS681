package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.List;

public final class Position {
	private final double lat, lon, alt;
	public Position(double lat, double lon, double alt){ 
		this.lat = lat; 
		this.lon = lon; 
		this.alt = alt; 
	}
	
	public double getlat(){ 
		return lat;
	} 
	
	public double getlon(){ 
		return lon; 
	} 
	
	public double getalt(){ 
		return alt; 
	} 
	
	public String toString() {
        return this.lat + "," + this.lon + "," + this.alt;
    }

    public boolean equals(Position comparePosition) {
        if (this.toString().equals(comparePosition.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Double> getCoordinate(){
    	List<Double> lstCoordinate = new ArrayList<Double>();
    	lstCoordinate.add(this.lat);
    	lstCoordinate.add(this.lon);
    	lstCoordinate.add(this.alt);
    	return lstCoordinate;
    }
    
    public Position changeLat(double newLat) {
    	return new Position(newLat, this.lon, this.alt);
    }
    
    public Position  changeLon(double newLon) {
    	return new Position(this.lat, newLon, this.alt);
    }
    
    public Position changeAlt(double newAlt) {
    	return new Position(this.lat, this.lon, newAlt);
    }
    
    public double distanceTo(Position anotherPosition) {
    	return (this.lat-anotherPosition.lat)+(this.lon-anotherPosition.lon)+(this.alt-anotherPosition.alt);
    }
}
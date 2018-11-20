package Shipment;

import Global.*;

public class Shipment {
	private String name;
	
	public Shipment(String name) {
		this.name = name;
	}
	
	public Shipment(String[] strs) {
	}
	
	
    public String getName() {
		TransQueue tq = new TransQueue();
		return "Shipment + " + tq.getName();
	}
}
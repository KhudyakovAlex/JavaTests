package Shipment;

import Global.*;

public class Shipment {
	private String name;
	
	public Shipment(String name) {
		this.name = name;
	}
	
	public Shipment(PlantData pd) {
	}
	
	public void save(PlantData pd) {
	}	
	
    public String getName() {
		TransQueue tq = new TransQueue();
		return "Shipment + " + tq.getName();
	}
}
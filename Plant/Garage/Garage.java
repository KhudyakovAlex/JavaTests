package Garage;

import Global.*;

public class Garage implements Runnable, Costable {
	// Автопарк автомобилей
	Vehicle[] vehicles = new Vehicle[100];
	vCount = 0;
	
	int vCount = 0;
	private String name;
	public Thread t;
	
	private Vehicle.Passported[] nums = new Vehicle.Passported[1000];
	
	public int getCost() {

		return 0;
	}
	
	public String getCostName() {
		return "Garage";
	}
	
	public void run() {
		try {
            System.out.println(name + " начал работу");
            while (PlantState.works) {
			    Thread.sleep(500);
		    }
		} catch (InterruptedException e) {}		
	}
	
	private void startThread() {
		t = new Thread(this, "Garage");
		t.start();
	}
	
	public Garage(String name) {
		this.name = name;
		startThread();
	}
	
	public Garage(PlantData pd) {
		
		String[] strs = pd.getData("GA", "NAME");		
		for (String str : strs) name = str;
		
		startThread();
	}
	
	public void save(PlantData pd) {
		pd.add("GA", "NAME", name);
	}
}
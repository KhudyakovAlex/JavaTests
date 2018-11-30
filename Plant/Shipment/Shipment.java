package Shipment;

import Global.*;

public class Shipment extends StockShop implements Runnable, Costable {
	private String name;
	public Thread t;
	
	public int getCost() {
		int c = 0;
		
		c = super.getCost() + super.getBusyVolume() * Price.PRODUCT;
		
		return c;
	}
	
	public String getCostName() {
		return "Shipment";
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
		t = new Thread(this, "Shipment");
		t.start();
	}
	
	public Shipment(String name, PlantMap m) {
		this.name = name;
		this.m = m;
		startThread();
	}
	
	public Shipment(PlantData pd, PlantMap m) {
		this.m = m;
		
		String[] strs = pd.getData("SH", "NAME");			
		for (String str : strs) name = str;
		
		// Читаем склады	
		strs = pd.getData("SH", "STOCK");			
		for (String str : strs) {			
			addStock(Integer.parseInt(str), "SH");
		}
			
		startThread();
	}
	
	public void save(Global.PlantData pd) {		
		pd.add("SH", "NAME", name);
		// Сохраняем склады
		for (int i = 0; i < stCount; i++) {
			pd.add("SH", "STOCK", Integer.toString(sts[i].getVolume()));
		}
	}
}
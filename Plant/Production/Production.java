package Production;

import Global.*;

public class Production extends StockShop implements Runnable, Costable {
	private String name;
	public Thread t;
	private StockQueue sq;
	
	public int getCost() {
		int c = 0;
		
		c = super.getCost() + super.getBusyVolume() * Price.RESOURCE;
		
		return c;
	}
	
	public String getCostName() {
		return "Production";
	}
	
	public void run() {
		try {
            System.out.println(name + " начал работу");
            while (PlantState.works) {
			    Thread.sleep(500);
				synchronized(sq) {
				    sq.get(1);
    			}
		    }
		} catch (InterruptedException e) {}		
	}
	
	private void startThread() {
		t = new Thread(this, "Production");
		t.start();
	}
	
	public Production(String name, PlantMap m, StockQueue sq) {
		this.name = name;
		this.m = m;
		this.sq = sq;		
		startThread();
	}
	
	public Production(Global.PlantData pd, PlantMap m, StockQueue sq) {
		this.m = m;
		this.sq = sq;		
		
		String[] strs = pd.getData("PR", "NAME");			
		for (String str : strs) name = str;
		
		// Читаем склады	
		strs = pd.getData("PR", "STOCK");			
		for (String str : strs) {			
			addStock(Integer.parseInt(str), "PR");
		}
			
		startThread();
	}
	
	public void save(Global.PlantData pd) {		
		pd.add("PR", "NAME", name);
		// Сохраняем склады
		for (int i = 0; i < stCount; i++) {
			pd.add("PR", "STOCK", Integer.toString(sts[i].getVolume()));
		}
	}
}
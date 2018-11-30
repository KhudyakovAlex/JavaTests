package Supply;

import Global.*;

public class Supply extends StockShop implements Runnable, Costable {
	private String name;
	public Thread t;
	private StockQueue sq;
	
	public int getCost() {
		int c = 0;
		
		c = super.getCost() + super.getBusyVolume() * Price.RESOURCE;
		
		return c;
	}
	
	public String getCostName() {
		return "Supply";
	}
	
	public void run() {
		try {
            System.out.println(name + " начал работу");
            while (PlantState.works) {
			    Thread.sleep(500);
				try {
				    synchronized(sq) {
					    sq.put(0);
    				}
				} catch (StockQueueException e) {
					System.out.println(e);
				}
		    }
		} catch (InterruptedException e) {}		
	}
	
	private void startThread() {
		t = new Thread(this, "Supply");
		t.start();
	}
	
	public Supply(String name, PlantMap m, StockQueue sq) {
		super();
		this.name = name;
		this.m = m;
		this.sq = sq;
		startThread();
	}
	
	public Supply(Global.PlantData pd, PlantMap m, StockQueue sq) {
		super();
		this.m = m;
		this.sq = sq;
		
		String[] strs = pd.getData("SU", "NAME");			
		for (String str : strs) name = str;
		
		// Читаем склады	
		strs = pd.getData("SU", "STOCK");			
		for (String str : strs) {			
			addStock(Integer.parseInt(str), "SU");
		}
			
		startThread();
	}
	
	public void save(Global.PlantData pd) {		
		pd.add("SU", "NAME", name);
		// Сохраняем склады
		for (int i = 0; i < stCount; i++) {
			pd.add("SU", "STOCK", Integer.toString(sts[i].getVolume()));
		}
	}
}
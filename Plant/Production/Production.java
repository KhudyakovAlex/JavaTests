package Production;

import Global.*;

public class Production {
	private String name;
	
	public Production(String name) {
		this.name = name;
	}
	
	public Production(String[] strs) {
	}
	
    public String getName() {
		TransQueue tq = new TransQueue();
		return "Production + " + tq.getName();
	}
}
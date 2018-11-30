package Global;

public class CostReestr {
	private Costable[] cs = new Costable[1000];
	private int cCount = 0;
	
	public void add(Costable c) {
		cs[cCount++] = c;
	}
	
	public void remove(Costable c) {
		for (int i = 0; i < cCount; i++) 
			if (c == cs[i]) {
				for (int j = i; j < cCount; j++)
                    cs[j] = cs[j + 1];
				cCount--;
			}
	}
	
	public String[] getReestr() {
		String[] strs = new String[cCount];
		
		for (int i = 0; i < cCount; i++) {
		    strs[i] = cs[i].getCostName() + " " + cs[i].getCost();	
		}
		
		return strs;
	}
	
	public int getCost(String... divs) {
		int cost = 0;
		
		for (int i = 0; i < cCount; i++) {
			for (String div : divs) {
				if (div.equals(cs[i].getCostName())) {
		            cost += cs[i].getCost();
				}
			}
		}
		
		return cost;
	}
}
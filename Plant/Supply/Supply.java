package Supply;

import Global.*;

public class Supply {
	private String name;
	
	public Supply(String name) {
		this.name = name;
	}
	
	public Supply(PlantData pd) {
		//for(String str : strs) {
		//	if(str != null && str.substring(0, 2).equals("SU")) {
		//		if(str.substring(3, 7).equals("NAME")) {
		//			name = str.substring(8, str.length());
		//			break;
		//		}
		//	}
		//}
	}
	
	public void save(PlantData pd) {
		//int i;
		//for(i = 0; strs[i] != null; i++);
		//strs[i] = "SU NAME " + name;
		//return strs;
	}
	
    public String getName() {
		TransQueue tq = new TransQueue();
		return "Supply + " + tq.getName();
	}
}
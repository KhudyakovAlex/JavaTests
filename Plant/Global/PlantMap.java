package Global;

public class PlantMap {
	private Stock[][] m = new Stock[10][10];

    public void allocate(Stock s) {
		OUTER: {
		    for (int y = 0; y < 10; y++) {
			    for (int x = 0; x < 10; x++) {				
				    if (m[y][x] == null) {		
					    m[y][x] = s;
						s.y = y;
						s.x = x;
						break OUTER;
    				}					
	    		}
		    }
		}
	}
	
	public void print() {
		for (Stock[] ss : m) {
			for (Stock s : ss) {
				String p;
				
				p = (s == null) ? "-" : (
				    s.division.equals("SU") ? "I" : (
					s.division.equals("PR") ? "P" : (
					"O" ))); 
				System.out.print(p);				
			}
			System.out.print("\n");
		}
	}
}
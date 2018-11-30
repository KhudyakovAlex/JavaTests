package Global;

public class Vehicle {
	private String stateNumber;
	
	public Vehicle(String stateNumber) {
		this.stateNumber = stateNumber;
	}
	
	public interface Numerable {
		public void setNumber(String num);
		public String getNumber();
	}
	
	public interface Passported extends Numerable {
	    public void setPassNumber(String num);
		public String getPassNumber();
		
		static void printPassFormat(char resident) {
			switch (resident) {
				case 'T':
				    System.out.println("Russia xNNNxxNN");
				    break;
				case 'F':
				    System.out.println("No Russia NNNxxxNNN");
				    break;   
			}
		}
	}
}
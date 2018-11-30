package Global;

public interface Costable {
	public int getCost();
	public default String getCostName() {
		return "None";
	};
}
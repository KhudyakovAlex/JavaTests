package Pck;

public class pa{
	private static String name;
	
	static {
		name = "PCK.PA";
	}
	
	pa(String name){
		this.name = name;
	}
	
	public static String getName(){
		return name;
	}
}
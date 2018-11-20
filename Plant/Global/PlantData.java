package Global;

import java.io.*;
import java.util.*;

public class PlantData {
	class DataSet {
		String shopName;
		String dataName;
		String data;
	}
	
	DataSet[] dss = new DataSet[1000];
	
	public PlantData(String fileName) {
		String str;
		String[] strParts;
		int i = 0;
		
		try (Scanner sc = new Scanner(new File("plant.dat"))) {
			while (sc.hasNextLine()) {
				str = sc.nextLine();
				// ѕарсим строку
				strParts = str.split(" ");			
				//dss[i].shopName = strParts[0];
				//dss[i].dataName = strParts[1];
				//dss[i].data = strParts[2];
				i++;
			}
		} catch(IOException e) {			
		}
	}
		
	public void save() {
	}
	
	public void clear() {
		for (int i = 0; i < 1000; i++) {
			//dss[i].shopName = null;
			//dss[i].dataName = null;
			//dss[i].data = null;
		}			
	}
	
	public boolean isEmpty() {
		for (DataSet ds : dss) {
			//if (ds.shopName != null) return false;
		}
		return true;
	}
	
	// ѕолучение наборов данных по названию цеха и названию типа данных
	public String[] getData(String shopName, String dataName) {
		String[] strs = new String[1000];
		
		return strs;
	}
}
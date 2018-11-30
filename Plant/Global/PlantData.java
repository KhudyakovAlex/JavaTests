package Global;

import java.io.*;
import java.util.*;

public class PlantData {
	class DataSet {
		String shopName;
		String dataName;
		String data;
	}
	
	DataSet[] dss = new DataSet[100];;
	
	// Конструктор с загрузкой из файла
	public PlantData(String fileName) {
		String str;
		String[] strParts;
		
		try (Scanner sc = new Scanner(new File("plant.dat"))) {
			while (sc.hasNextLine()) {
				str = sc.nextLine();
				// Парсим строку
				strParts = str.split(" "); 
				add(strParts[0], strParts[1], strParts[2]);				
			}
		} catch(IOException e) {			
		}
	}
		
	// Сохранение списка данных в файл
	public void save(String fileName) {
		try (FileWriter wr = new FileWriter(fileName)) {
			for(DataSet ds : dss)
				if(ds != null && ds.shopName != null) {					
					wr.write(ds.shopName + " " + ds.dataName + " " + ds.data +"\n");
				}
		} catch(IOException e) {
			System.out.println("Не удалось сохранить завод в файл");
		}
	}
	
	// Добавление в список данных
	public void add(String shopName, String dataName, String data) {
		int i = -1;
		
		while (dss[++i] != null && dss[i].shopName != null);
		dss[i] = new DataSet();
		dss[i].shopName = shopName;
		dss[i].dataName = dataName;
		dss[i].data = data;	
			
	}
	
	// Очистка списка данных
	public void clear() {
		for (int i = 0; i < 100; i++) {
			if (dss[i] != null) {
			    dss[i].shopName = null;
			    dss[i].dataName = null;
			    dss[i].data = null;
			}
		}			
	}
	
	// Пуст ли список данных
	public boolean isEmpty() {
		for (DataSet ds : dss) {
			if (ds != null && ds.shopName != null) return false;
		}
		return true;
	}
	
	// Получение наборов данных по названию цеха и названию типа данных
	public String[] getData(String shopName, String dataName) {
		String[] strs;
		int i = 0;
		
		// Считаем количество строк, которые вернем
		for (DataSet ds : dss) {
			if (ds != null && ds.shopName != null && ds.shopName.equals(shopName) &&
			    ds.dataName != null && ds.dataName.equals(dataName)) {				
				i++;
			}
		}
		
		// Заполняем массив
		strs = new String[i];
		i = 0;
		for (DataSet ds : dss) {
			if (ds != null && ds.shopName != null && ds.shopName.equals(shopName) &&
			    ds.dataName != null && ds.dataName.equals(dataName)) {				
				strs[i] = ds.data;
				i++;
			}
		}		

		return strs;
	}
}
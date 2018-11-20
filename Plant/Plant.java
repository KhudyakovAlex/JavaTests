import java.io.*;
import java.util.*;

import Global.*;
import Supply.*;
import Production.*;
import Shipment.*;

public class Plant {
    public static void main(String[] args) {
        System.out.println("===== Plant ======================================");
	
		Supply su; // Поставка
		Production pr; // Производство
		Shipment sh; // Отгрузка

		TransQueue tq; // Очередь заявок на транспорт
		
	    // Попытка чтения состояния завода из файла plant.dat
		try (Scanner sc = new Scanner(new File("plant.dat"))) {
			// Файл существует - загружаем данные завода
			System.out.println("Чтение состояния завода из файла");
			List<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) lines.add(sc.nextLine());
			String[] fs = lines.toArray(new String[0]);
			su = new Supply(fs);
			pr = new Production(fs);
			sh = new Shipment(fs);
		} catch(IOException e) {
			// Файла нет - создаем новый завод
			System.out.println("Создание нового завода");
			su = new Supply("Поставка");
			pr = new Production("Производство");
			sh = new Shipment("Отгрузка");
		}
		
		// Сохранение состояние завода в файл plant.dat
		try (FileWriter wr = new FileWriter("plant.dat")) {
			String[] fs = new String[1000];
			fs = su.save(fs);
			
			for(String str : fs)
				if(str != null)
					wr.write(str);
		} catch(IOException e) {
			System.out.println("Не удалось сохранить завод в файл");
		}
	}
}
import java.io.*;
import java.util.*;

import Global.*;
import Supply.*;
import Production.*;
import Shipment.*;
import Garage.*;
import PlantManager.*;

public class Plant {	
    public static void main(String[] args) {
        System.out.println("===== Plant ======================================");
	
		Supply su; // Цех Поставка
		Production pr; // Цех Производство
		Shipment sh; // Цех Отгрузка
		Garage ga; // Цех Гараж
		
		StockQueue sq = new StockQueue(); // Буфер сырья между поставкой и производством
		
		PlantManager pm; // Управление заводом
		TransQueue tq = new TransQueue(); // Очередь заявок на транспорт
		Cash c; // Деньги в кассе
		CostReestr cr = new CostReestr(); // Реестр объектов, имеющих стоимость
		PlantMap m = new PlantMap(); // Карта завода

		PlantState.works = true; // Завод работает
		
		// Попытка загрузки данных завода из файла
		PlantData pd = new PlantData("plant.dat");
		
		if (pd.isEmpty()) {
			// Файла нет - создаем новый завод
			System.out.println("Создание нового завода");
			c = new Cash();
			su = new Supply("Поставка", m, sq);
			pr = new Production("Производство", m, sq);
			sh = new Shipment("Отгрузка", m);
			ga = new Garage("Гараж");
		} else {
			// Файл существует - загружаем данные завода
			System.out.println("Загружаем завод из файла");
			c = new Cash(getCash(pd));
			su = new Supply(pd, m, sq);
			pr = new Production(pd, m, sq);
			sh = new Shipment(pd, m);
			ga = new Garage(pd);
		}
		
		// Добавляем в реестр стоимости
		cr.add(su);
		cr.add(pr);
		cr.add(sh);
		cr.add(ga);
		
		// Управление заводом
		pm = new PlantManager(su, pr, sh, ga, c, cr, m, sq);
		pm.manage();
		
		try {
	        su.t.join();
    		pr.t.join();
		    sh.t.join();
		    ga.t.join();
		} catch (InterruptedException e) {}	
		
		// Сохранение состояние завода в файл plant.dat
		pd.clear();		
		su.save(pd);
		pr.save(pd);
		sh.save(pd);
		ga.save(pd);
		saveCash(pd, c);
		pd.save("plant.dat");
	}
	
	// Загрузка данных кассы
	private static int getCash(PlantData pd) {
		String[] strs = pd.getData("PL", "CASH");			
		for (String str : strs) return Integer.parseInt(str);
		return 0;
	}
	
	// Сохранение данных кассы
	private static void saveCash(PlantData pd, Cash c) {
		pd.add("PL", "CASH", Integer.toString(c.getCash()));
	}	
}
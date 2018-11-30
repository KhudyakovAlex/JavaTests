package PlantManager;

import java.io.*;
import Global.*;
import Supply.*;
import Production.*;
import Shipment.*;
import Garage.*;

public class PlantManager implements Runnable {
	Supply su; 
	Production pr; 
	Shipment sh; 
	Garage ga;
	Cash c;
	CostReestr cr;
	PlantMap m;
	StockQueue sq;
	
	public void run() {
	}	
	
	public PlantManager(Supply su, Production pr, Shipment sh, Garage ga, Cash c, CostReestr cr, PlantMap m, StockQueue sq) {
		this.su = su;
		this.pr = pr;
		this.sh = sh;
		this.ga = ga;
		this.c = c;
		this.cr = cr;
		this.m = m;
		this.sq = sq;
	}
	
	public void manage() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		do {
			try {
			    str = br.readLine();

                // Пустой ввод
			    if (str.equals("")) {
					System.out.println("Чё?");
				} else				
				
				// Останов завода
			    if (str.equals("stop")) {
					break;
				} else				    
					
			    // Вывод статистики по заводу
				if (str.equals("stat")) {
					printPlantStat();
				} else
				
				// Взять кредит
				if (str.split(" ")[0].equals("credit")) {
					getCredit(Integer.parseInt(str.split(" ")[1]));
				} else
					
				// Купить склад поставки
				if (str.equals("buy su stock")) {
					buySuStock();
				} else
					
				// Купить склад производства
				if (str.equals("buy pr stock")) {
					buyPrStock();
				} else
					
				// Купить склад отгрузки
				if (str.equals("buy sh stock")) {
					buyShStock();
				} else
					
				// Получение реестра стоимости
				if (str.equals("cost reestr")) {
					printCostReestr();
				} else
					
				// Получение стоимости
				if (str.equals("cost")) {
					printCost();
				} else
					
				// Получение стоимости без поставки
				if (str.equals("cost nosuplly")) {
					printCostNoSuplly();
				} else
				
				// Вывод карты завода
				if (str.equals("map")) {
					m.print();
				} else
					
				// Вывод буфера сырья
				if (str.equals("sq")) {
					printStoreQueue();
				} else
					
				// Добавление в буфер сырья
				if (str.substring(0, 6).equals("sq put")) {
					try {
					    sq.put(Integer.parseInt(str.split(" ")[2]));
					} catch (StockQueueException e) {
						System.out.println(e);
					}
				} else
					
				// Вывод формата автомобильного номера
				if (str.equals("car number")) {
					Vehicle.Passported.printPassFormat('T');
					Vehicle.Passported.printPassFormat('F');
				} else
								
				System.out.println("Чё?");

			} catch (IOException e) {				
			} finally {
				PlantState.works = false;
			}
			
		} while (true);
	}
	
	// Вывод статистики по заводу
	private void printPlantStat() {
		System.out.println("=== Статистика завода ===");
		System.out.println("Касса: " + c.getCash());
		System.out.println(" ");
		System.out.println("    Поставка складов: " + su.getStockCount());
		System.out.println("       занятый объем: " + su.getBusyVolume());
		System.out.println("     свободный объем: " + su.getFreeVolume());
		System.out.println(" ");
		System.out.println("Производство складов: " + pr.getStockCount());
		System.out.println("       занятый объем: " + pr.getBusyVolume());
		System.out.println("     свободный объем: " + pr.getFreeVolume());
        System.out.println(" ");		
		System.out.println("    Отгрузка складов: " + sh.getStockCount());
		System.out.println("       занятый объем: " + sh.getBusyVolume());
		System.out.println("     свободный объем: " + sh.getFreeVolume());
        System.out.println(" ");	
		System.out.println("           Стоимость: " + cr.getCost());			
		System.out.println("              Баланс: " + (cr.getCost() + c.getCash()));		
		System.out.println("=========================");		
	}
	
	
	// Вывод буфера сырья
    private void printStoreQueue() {
		System.out.println(sq.getState());
	}
					
	// Взять кредит
	private void getCredit(int inc) {
		c.incCash(inc);
	}
	
	// Купить склад поставки
	private	void buySuStock() {
		c.decCash(Price.STOCK);
		su.addStock(0, "SU");
	}
	
	// Купить склад производства
	private	void buyPrStock() {
		c.decCash(Price.STOCK);
		pr.addStock(0, "PR");
	}
	
	// Купить склад отгрузки
	private	void buyShStock() {
		c.decCash(Price.STOCK);
		sh.addStock(0, "SH");
	}
	
	// Получение реестра стоимости
	private	void printCostReestr() {
		for (String str : cr.getReestr())
			System.out.println(str);
	}
	
	// Получение стоимости
	private	void printCost() {
		System.out.println("Общая стоимость завода: " + cr.getCost("Supply", "Production", "Shipment"));
	}
	
	// Получение стоимости без поставки
	private	void printCostNoSuplly() {
		System.out.println("Общая стоимость завода: " + cr.getCost("Production", "Shipment"));
	}
	
}
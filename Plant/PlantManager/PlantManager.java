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

                // ������ ����
			    if (str.equals("")) {
					System.out.println("׸?");
				} else				
				
				// ������� ������
			    if (str.equals("stop")) {
					break;
				} else				    
					
			    // ����� ���������� �� ������
				if (str.equals("stat")) {
					printPlantStat();
				} else
				
				// ����� ������
				if (str.split(" ")[0].equals("credit")) {
					getCredit(Integer.parseInt(str.split(" ")[1]));
				} else
					
				// ������ ����� ��������
				if (str.equals("buy su stock")) {
					buySuStock();
				} else
					
				// ������ ����� ������������
				if (str.equals("buy pr stock")) {
					buyPrStock();
				} else
					
				// ������ ����� ��������
				if (str.equals("buy sh stock")) {
					buyShStock();
				} else
					
				// ��������� ������� ���������
				if (str.equals("cost reestr")) {
					printCostReestr();
				} else
					
				// ��������� ���������
				if (str.equals("cost")) {
					printCost();
				} else
					
				// ��������� ��������� ��� ��������
				if (str.equals("cost nosuplly")) {
					printCostNoSuplly();
				} else
				
				// ����� ����� ������
				if (str.equals("map")) {
					m.print();
				} else
					
				// ����� ������ �����
				if (str.equals("sq")) {
					printStoreQueue();
				} else
					
				// ���������� � ����� �����
				if (str.substring(0, 6).equals("sq put")) {
					try {
					    sq.put(Integer.parseInt(str.split(" ")[2]));
					} catch (StockQueueException e) {
						System.out.println(e);
					}
				} else
					
				// ����� ������� �������������� ������
				if (str.equals("car number")) {
					Vehicle.Passported.printPassFormat('T');
					Vehicle.Passported.printPassFormat('F');
				} else
								
				System.out.println("׸?");

			} catch (IOException e) {				
			} finally {
				PlantState.works = false;
			}
			
		} while (true);
	}
	
	// ����� ���������� �� ������
	private void printPlantStat() {
		System.out.println("=== ���������� ������ ===");
		System.out.println("�����: " + c.getCash());
		System.out.println(" ");
		System.out.println("    �������� �������: " + su.getStockCount());
		System.out.println("       ������� �����: " + su.getBusyVolume());
		System.out.println("     ��������� �����: " + su.getFreeVolume());
		System.out.println(" ");
		System.out.println("������������ �������: " + pr.getStockCount());
		System.out.println("       ������� �����: " + pr.getBusyVolume());
		System.out.println("     ��������� �����: " + pr.getFreeVolume());
        System.out.println(" ");		
		System.out.println("    �������� �������: " + sh.getStockCount());
		System.out.println("       ������� �����: " + sh.getBusyVolume());
		System.out.println("     ��������� �����: " + sh.getFreeVolume());
        System.out.println(" ");	
		System.out.println("           ���������: " + cr.getCost());			
		System.out.println("              ������: " + (cr.getCost() + c.getCash()));		
		System.out.println("=========================");		
	}
	
	
	// ����� ������ �����
    private void printStoreQueue() {
		System.out.println(sq.getState());
	}
					
	// ����� ������
	private void getCredit(int inc) {
		c.incCash(inc);
	}
	
	// ������ ����� ��������
	private	void buySuStock() {
		c.decCash(Price.STOCK);
		su.addStock(0, "SU");
	}
	
	// ������ ����� ������������
	private	void buyPrStock() {
		c.decCash(Price.STOCK);
		pr.addStock(0, "PR");
	}
	
	// ������ ����� ��������
	private	void buyShStock() {
		c.decCash(Price.STOCK);
		sh.addStock(0, "SH");
	}
	
	// ��������� ������� ���������
	private	void printCostReestr() {
		for (String str : cr.getReestr())
			System.out.println(str);
	}
	
	// ��������� ���������
	private	void printCost() {
		System.out.println("����� ��������� ������: " + cr.getCost("Supply", "Production", "Shipment"));
	}
	
	// ��������� ��������� ��� ��������
	private	void printCostNoSuplly() {
		System.out.println("����� ��������� ������: " + cr.getCost("Production", "Shipment"));
	}
	
}
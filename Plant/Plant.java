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
	
		Supply su; // ��� ��������
		Production pr; // ��� ������������
		Shipment sh; // ��� ��������
		Garage ga; // ��� �����
		
		StockQueue sq = new StockQueue(); // ����� ����� ����� ��������� � �������������
		
		PlantManager pm; // ���������� �������
		TransQueue tq = new TransQueue(); // ������� ������ �� ���������
		Cash c; // ������ � �����
		CostReestr cr = new CostReestr(); // ������ ��������, ������� ���������
		PlantMap m = new PlantMap(); // ����� ������

		PlantState.works = true; // ����� ��������
		
		// ������� �������� ������ ������ �� �����
		PlantData pd = new PlantData("plant.dat");
		
		if (pd.isEmpty()) {
			// ����� ��� - ������� ����� �����
			System.out.println("�������� ������ ������");
			c = new Cash();
			su = new Supply("��������", m, sq);
			pr = new Production("������������", m, sq);
			sh = new Shipment("��������", m);
			ga = new Garage("�����");
		} else {
			// ���� ���������� - ��������� ������ ������
			System.out.println("��������� ����� �� �����");
			c = new Cash(getCash(pd));
			su = new Supply(pd, m, sq);
			pr = new Production(pd, m, sq);
			sh = new Shipment(pd, m);
			ga = new Garage(pd);
		}
		
		// ��������� � ������ ���������
		cr.add(su);
		cr.add(pr);
		cr.add(sh);
		cr.add(ga);
		
		// ���������� �������
		pm = new PlantManager(su, pr, sh, ga, c, cr, m, sq);
		pm.manage();
		
		try {
	        su.t.join();
    		pr.t.join();
		    sh.t.join();
		    ga.t.join();
		} catch (InterruptedException e) {}	
		
		// ���������� ��������� ������ � ���� plant.dat
		pd.clear();		
		su.save(pd);
		pr.save(pd);
		sh.save(pd);
		ga.save(pd);
		saveCash(pd, c);
		pd.save("plant.dat");
	}
	
	// �������� ������ �����
	private static int getCash(PlantData pd) {
		String[] strs = pd.getData("PL", "CASH");			
		for (String str : strs) return Integer.parseInt(str);
		return 0;
	}
	
	// ���������� ������ �����
	private static void saveCash(PlantData pd, Cash c) {
		pd.add("PL", "CASH", Integer.toString(c.getCash()));
	}	
}
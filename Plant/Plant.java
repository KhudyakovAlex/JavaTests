import java.io.*;
import java.util.*;

import Global.*;
import Supply.*;
import Production.*;
import Shipment.*;

public class Plant {
    public static void main(String[] args) {
        System.out.println("===== Plant ======================================");
	
		Supply su; // ��������
		Production pr; // ������������
		Shipment sh; // ��������

		TransQueue tq; // ������� ������ �� ���������
		
	    // ������� ������ ��������� ������ �� ����� plant.dat
		try (Scanner sc = new Scanner(new File("plant.dat"))) {
			// ���� ���������� - ��������� ������ ������
			System.out.println("������ ��������� ������ �� �����");
			List<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) lines.add(sc.nextLine());
			String[] fs = lines.toArray(new String[0]);
			su = new Supply(fs);
			pr = new Production(fs);
			sh = new Shipment(fs);
		} catch(IOException e) {
			// ����� ��� - ������� ����� �����
			System.out.println("�������� ������ ������");
			su = new Supply("��������");
			pr = new Production("������������");
			sh = new Shipment("��������");
		}
		
		// ���������� ��������� ������ � ���� plant.dat
		try (FileWriter wr = new FileWriter("plant.dat")) {
			String[] fs = new String[1000];
			fs = su.save(fs);
			
			for(String str : fs)
				if(str != null)
					wr.write(str);
		} catch(IOException e) {
			System.out.println("�� ������� ��������� ����� � ����");
		}
	}
}
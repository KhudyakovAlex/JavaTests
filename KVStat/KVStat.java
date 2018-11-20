import java.io.*;

public class KVStat {
  public static void main(String[] args){

    // Массив ГОД+МЕСЯЦ+НОВОСТЕЙ+ПРОСМОТРЫ
    int[][] res = new int[100000][4];
    int resSize = 0;
    int maxNews = 0;
    int maxViews = 0;

	// Если параметров нет, выдаем напоминалку
	if(args.length == 0){
		System.out.println("Должен быть передан один или несколько параметров. Б1, Б2...");
		return;
	}
	
    // Проходим по всем названиям файлов, полученных в параметрах
    for(String s : args){

      // Для каждого названия проходим по всем индексам (страницам)
      for(int i = 1; i < 100; i++){
		  
		//System.out.println("Открываем " + s + i + ".html");
      
        try(FileInputStream fin = new FileInputStream(s + i + ".html")){
			
		  System.out.println("Обрабатываем " + s + i + ".html");

          // Парсим файл и получаем массив ГОД+МЕСЯЦ+ПРОСМОТРЫ
          byte[] buffer = new byte[fin.available()];
          int pos = 0;
          int posNum = 0;
          fin.read(buffer, 0, fin.available());
          String str = new String(buffer);   

          while(str.indexOf("8<br>", pos) != -1){

            // Вытаскиваем дату
            pos = str.indexOf("8<br>", pos);
            // Год
            res[resSize][0] = Integer.parseInt(str.substring(pos - 1, pos + 1));
            // Месяц
            res[resSize][1] = Integer.parseInt((str.substring(pos - 4, pos - 2)).replace(".", ""));

            // Вытаскиваем количество просмотров
            pos = str.indexOf("column wars", pos);
            if((pos - str.lastIndexOf("</p>", pos)) < 100){
              pos = str.lastIndexOf("</p>", pos);
              posNum = str.lastIndexOf(" ", pos);
              res[resSize][2] = Integer.parseInt(str.substring(posNum + 1, pos));
            }

            // Ставим для будущих группировок 1 новость
            res[resSize][3] = 1;

            resSize++;
          }       

          System.out.println("Файл " + s + i + ".html обработан.");

        }catch(IOException ex){};
      }
    }

    // Сортируем массив по месяцу ТОЛЬКО!!! Потом поправить
    int res0, res1, res2, res3;

    for(int i = 0; i < resSize; i++){
      for(int j = 1; j < resSize; j++){
        if(res[j][1] > res[j - 1][1]){
          res0 = res[j][0]; res[j][0] = res[j - 1][0]; res[j - 1][0] = res0;
          res1 = res[j][1]; res[j][1] = res[j - 1][1]; res[j - 1][1] = res1;
          res2 = res[j][2]; res[j][2] = res[j - 1][2]; res[j - 1][2] = res2;
          res3 = res[j][3]; res[j][3] = res[j - 1][3]; res[j - 1][3] = res3;
        }
      }
    }
    
    // Исходный массив переделываем в ГОД+МЕСЯЦ+НОВОСТЕЙ+ПРОСМОТРЫ
    // группируя по месяцу и сортируя
    int resSizeFin = 0;
    for(int i = 1; i < resSize; i++){
      if(res[i][0] == res[resSizeFin][0] && res[i][1] == res[resSizeFin][1]){
        res[resSizeFin][2] += res[i][2];
        res[resSizeFin][3]++;
        res[i][0] = 0;
      }else{
        if(res[resSizeFin][3] > maxNews) maxNews = res[resSizeFin][3];
        if(res[resSizeFin][2] > maxViews) maxViews = res[resSizeFin][2];
        resSizeFin = i;
      }
    }

    // Выводим полученный массив в консоль
    System.out.println(" ");
    System.out.println(changeStringLengthL("Новости", 15) + " |||");
    System.out.println(changeStringLengthL("Просмотры", 15) + " ===");
    System.out.println(" ");
    System.out.println(" ");

    for(int i = resSizeFin; i >=0; i--){
      if(res[i][0] != 0){
        System.out.println(changeStringLengthL(getMonthByNum(res[i][1]), 9) + " " + changeStringLengthL(res[i][3] + "", 5) + " " +
          getNSymbols("|", (int)((float)res[i][3] / (float)maxNews * 20)));
        System.out.println(changeStringLengthL(res[i][2] + "", 15) + " " + 
          getNSymbols("=", (int)((float)res[i][2] / (float)maxViews * 40)));
        System.out.println(" ");
      }
    }
  }

  // Делает строку заданной длины добавляя пробелы справа
  private static String changeStringLengthR(String str, int strLength){

    String res = str;
    if(res.length() >= strLength) res = res.substring(0, strLength);
    else {
      while(res.length() < strLength) res += " ";
    }
    return res;

  }

  // Делает строку заданной длины добавляя пробелы слева
  private static String changeStringLengthL(String str, int strLength){

    String res = str;
    if(res.length() >= strLength) res = res.substring(0, strLength);
    else {
      while(res.length() < strLength) res = " " + res;
    }
    return res;

  }

  // Возвращает название месяца по его номеру
  private static String getMonthByNum(int num) {

    String res;

    switch(num){
      case 1: res = "Январь"; break;
      case 2: res = "Февраль"; break;
      case 3: res = "Март"; break;
      case 4: res = "Апрель"; break;
      case 5: res = "Май"; break;
      case 6: res = "Июнь"; break;
      case 7: res = "Июль"; break;
      case 8: res = "Август"; break;
      case 9: res = "Сентябрь"; break;
      case 10: res = "Октябрь"; break;
      case 11: res = "Ноябрь"; break;
      case 12: res = "Декабрь"; break;

      default: res = "";
    }

    return res;

  }

  // Возвращает строку из заданного количества одинаковых символов
  private static String getNSymbols(String sym,int num) {

    String res = "";

    for(int i = 0; i < num; i++) res += sym;

    return res;

  }
}
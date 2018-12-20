import sun.management.FileSystem;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.regex.Pattern;

import com.thetransactioncompany.util.*;

public class search {
    public static void main (String args[])throws Exception{
        Data data = new Data();
        Date starttime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss/SSS");
        System.out.println(sdf.format(starttime) + "/Robot is started");
        String date = Data.getDate();

// Подключение драйвера MySQL
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e);
        }

//Создаем менеджера базы данных, параметры подключения заданы в классе
        SQLManager sqlManager = new SQLManager();


// Заполняем список класса UrlList выборкой из базы данны id, url.
// Дата передается нулевым аргументом main()
        UrlList urlList = sqlManager.getUrlList(date);
        System.out.println(sdf.format(new Date()) + "/Received URL-list size: " + urlList.size() + " row(s)");
// Создаем URL-манеджера для организации многопоточной проверки статусов URL-ссылок
        URLManager urlManager = new URLManager();

// Заполняем список статусов проверяя все ссылки из полученного списка URL-адресов
        StatList statList = urlManager.getStatList(urlList);
        System.out.println(sdf.format(new Date()) + "/Received Status-list size: " + statList.size() + " row(s)");
// Передаём список SQL-менеджеру для внесения изменений в базу данных
        int n[] = sqlManager.update(statList);
        int m = n[0] + n[1];
        Date endtime = new Date();
        long worktime = endtime.getTime() - starttime.getTime();
        System.out.println(sdf.format(endtime) + "/Updated rows number: " + m + " row(s)\n" +
                "      Number of rows with standard status: " + n[0] + " row(s)\n" +
                "      Number of rows with irregular status: " + n[1] + " row(s)");
        if (n[1] != 0){
            System.out.println("I can try to solve the problem, if you start me again.\n" +
                                "Also, I recommend reducing the network load or the pool size of threads.");
        }
        System.out.println(sdf.format(new Date()) + "/Robot stopped. Worked time: " + worktime + " ms");
    }
}

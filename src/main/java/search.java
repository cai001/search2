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
        System.out.println(sdf.format(new java.util.Date()) + "/Received URL-list size: " + urlList.size() + " row(s)");
// Создаем URL-манеджера для организации многопоточной проверки статусов URL-ссылок
        URLManager urlManager = new URLManager();

// Заполняем список статусов проверяя все ссылки из полученного списка URL-адресов
        StatList statList = urlManager.getStatList(urlList);
        System.out.println(sdf.format(new java.util.Date()) + "/Received Status-list size: " + statList.size() + " row(s)");
// Передаём список SQL-менеджеру для внесения изменений в базу данных
        int n = sqlManager.update(statList);
        Date endtime = new Date();
        long worktime = endtime.getTime() - starttime.getTime();
        System.out.println(sdf.format(endtime) + "/Updated rows number: " + n + " row(s)\n" +
                " Robot stopped. Worked time: " + worktime + " ms");
    }
}

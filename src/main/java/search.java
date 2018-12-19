import java.util.*;
import java.text.SimpleDateFormat;

public class search {
    public static void main(String args[]){
        Date starttime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss/SSS");
        System.out.println(sdf.format(starttime) + "/Robot is started");
        String date = "20181111";
//        if (args.length == 1){
//            date = args[0];
//        }
//        else{
//            System.out.println("You need to set a date after command so i get to work.\n" +
//                                "Date format should be as YYYYMMDD");
//            System.exit(1);
//        }

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

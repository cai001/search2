import java.util.*;
import java.text.SimpleDateFormat;

public class search {
    public static void main(String args[]){
        Date starttime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss/SSS");
        System.out.println(sdf.format(starttime) + "/Робот запущен");
        String date = "20181111";
//        if (args.length == 1){
//            date = args[0];
//        }
//        else{
//            System.out.println("Вы не задали дату, пожалуста добавьте к команде дату, отступив пробел.\n" +
//                                "Дату необходимо ввести в очередности ГОД-МЕСЯЦ-ДЕНЬ без пробелов");
//            System.exit(1);
//        }
//        System.out.println(new java.util.Date());
// Подключение драйвера MySQL
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("SQL драйвер не проходит регистрацию");
        }

//Создаем менеджера базы данных, параметры подключения заданы в классе
        SQLManager sqlManager = new SQLManager();


// Заполняем список класса UrlList выборкой из базы данны id, url.
// Дата передается нулевым аргументом main()
        UrlList urlList = sqlManager.getUrlList(date);
        System.out.println(sdf.format(new java.util.Date()) + "/Получен спиосок URL в кол-ве: " + urlList.size() + " шт.");
// Создаем URL-манеджера для организации многопоточной проверки статусов URL-ссылок
        URLManager urlManager = new URLManager();

// Заполняем список статусов проверяя все ссылки из полученного списка URL-адресов
        StatList statList = urlManager.getStatList(urlList);
        System.out.println(sdf.format(new java.util.Date()) + "/Получен спиосок статусов в кол-ве: " + statList.size() + " шт.");
// Передаём список SQL-менеджеру для внесения изменений в базу данных
        int n = sqlManager.update(statList);
        Date endtime = new Date();
        long worktime = endtime.getTime() - starttime.getTime();
        System.out.println(sdf.format(endtime) + "/Изменения внесены в базу данных в кол-ве: " + n + " шт.\n" +
                " Робот остановлен. Время работы составило: " + worktime + " мс");
    }
}

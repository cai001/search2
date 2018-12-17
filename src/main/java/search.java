import java.sql.*;
public class search {
    public static void main(String args[]){
        if (args == null){
            System.out.println("Вы не задали дату, пожалуста добавьте к команде дату, отступив пробел.\n" +
                    "Дату необходимо ввести в очередности ГОД-МЕСЯЦ-ДЕНЬ без пробелов");
            System.exit(130);
        }
        System.out.println(new java.util.Date());
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
        UrlList urlList = sqlManager.getUrlList(args[0]);

// Создаем URL-манеджера для организации многопоточной проверки статусов URL-ссылок
        URLManager urlManager = new URLManager();

// Заполняем список статусов проверяя все ссылки из полученного списка URL-адресов
        StatList statList = urlManager.getStatList(urlList);

// Передаём список SQL-менеджеру для внесения изменений в базу данных
        sqlManager.update(statList);

    }
}

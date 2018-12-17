import java.sql.*;
public class search {
    public static void main(String args[]){
        if (args == null){
            System.out.println("Вы не задали дату, пожалуста добавьте к команде дату, отступив пробел.\n" +
                    "Дату необходимо ввести в очередности ГОД-МЕСЯЦ-ДЕНЬ без пробелов");
        }
        System.out.println(new java.util.Date());
// Подключение драйвера MySQL
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("SQL драйвер не проходит регистрацию");
        }
        SQLManager sqlManager = new SQLManager();
// Заполняем список URL-адресами из базы данных через метод SQLManager.getUrlList(String date)
        UrlList urlList = sqlManager.getUrlList(args[0]);
// Заполняем список статусов проверяя все ссылки из полученного списка URL-адресов

// Передаём список SQL-менеджеру для внесения изменений в базу данных

    }
}

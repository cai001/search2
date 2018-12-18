import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SQLManager {
    private static String url = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    private static String login = "mysql";
    private static String password = "mysql";

// Метод, который создаёт соединение с базой данных, запрашивает набор URL-адресов
// и заносит их в возвращаемый список в виде объектов, с параметрами id и url
    public static UrlList getUrlList(String date){
        UrlList urllist = new UrlList();
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            try{
                ResultSet resultSet = statement.executeQuery("SELECT id, url FROM urllistg WHERE date < '" + date + "' or date is null");
                while (resultSet.next()){

//     В каждом цикле добавляется по одному URL-обекту в лист специального вида
                    urllist.add(new Urlobj(resultSet.getInt("id"), resultSet.getString("url")));
                }
            }catch (SQLException ex){
                System.out.println("Проверьте правильность ввода даты\n" +
                        "Дату необходимо ввести в очередности ГОД-МЕСЯЦ-ДЕНЬ без пробелов");
                System.exit(130);
            }

            statement.close();
        }catch (SQLException ex){
            System.out.println("Не получено соединение с базой данных");
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    System.out.println("Не получается закрыть соединение с базой данных");
                }
            }
        }
        return urllist;
    }

// Метод, который создаёт соединение с базой данных и вносит изменения в таблицу базы данных.
// Получаемый список состоит из объектов, с параметрами id и status.
// В таблице базы данных для каждого полученного id изменяется status в соответствии с полученным,
// дата последней проверки статуса изменяется на текущую.
    public static int update(StatList statList){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new java.util.Date());
        Connection connection = null;
        int n = 0;
        try{
            connection = DriverManager.getConnection(url, login, password);
            while (!statList.isEmpty()){             //Опусташаем список статусов с занесением в базу данных
                int status = statList.get(0).status; //Получаем status из первого объекта в списке
                int id = statList.get(0).id;         //Получаем id из первого объекта в списке
                statList.remove(0);               //Убираем первый объект из списка, на его место сдвигается второй
                Statement statement = connection.createStatement();
                n += statement.executeUpdate("UPDATE urllistg SET date = '" + date +
                        "', status = " + status + " WHERE id = " + id);

                statement.close();
            }
        }catch (SQLException ex){
            System.out.println("Не получено соединение с базой данных");
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    System.out.println("Не получается закрыть соединение с базой данных");
                }
            }
        }
        return n;
    }

}

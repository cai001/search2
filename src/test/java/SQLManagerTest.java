import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class SQLManagerTest {
    static String url = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    static String login = "mysql";
    static String password = "mysql";
    static String date = "20181218";
    Connection connection = null;
    @Test
    public void getUrlList() {
        int expected = 0;
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM urllistg WHERE (date < '" + date + "' or date is null)");
            resultSet.next();
            expected = resultSet.getInt(1);
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
        UrlList urlList = SQLManager.getUrlList(date);
        int actual = urlList.size();
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        int expected = 100;
        int id = 0;
        int status = 0;
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(id), status FROM urllistg");
            resultSet.next();
            id = resultSet.getInt(1);
            status = resultSet.getInt(2);
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
        StatList statList = new StatList();
        for(int i = 0; i < expected; i++){
            statList.add(new Statobj(id, status));
        }
        int actual = SQLManager.update(statList);
        assertEquals(expected, actual);
    }
}
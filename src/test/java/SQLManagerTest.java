import org.junit.After;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class SQLManagerTest {

    @After
    public void reg(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("SQL драйвер не проходит регистрацию");
        }
    }
    static String url = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    static String login = "mysql";
    static String password = "mysql";
    static String table = "urllistg";
    static String date = "20181218";
    Connection connection = null;

    @Test
    public void getUrlList() {
        int expected = 0;
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + table + " WHERE (date < '" + date + "' or date is null)");
            resultSet.next();
            expected = resultSet.getInt(1);
            statement.close();
        }catch (SQLException ex){
            System.out.println("Не получено соединение с базой данных 1");
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

//    @After
//    public void reg2(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }catch(Exception e){
//            System.out.println("SQL драйвер не проходит регистрацию");
//        }
//    }
    @Test
    public void update() {
        int expected = 100;
        int id = 1;
        int status = 0;
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(id), status FROM " + table);
            resultSet.next();
            id = resultSet.getInt(1);
            status = resultSet.getInt(2);
            statement.close();
        }catch (SQLException ex){
            System.out.println("Не получено соединение с базой данных 2");
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
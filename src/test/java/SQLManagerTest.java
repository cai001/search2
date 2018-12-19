import org.junit.After;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class SQLManagerTest {

//There is needed to register SQL-Driver
    @After
    public void reg(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("Inside unit test: " + e);
        }
    }

//Description connection parameters for your data base
    static String url = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    static String login = "mysql";
    static String password = "mysql";
    static String table = "urllistg";
//A date for select query
    static String date = "20181218";

    Connection connection = null;

    @Test
    public void getUrlList() {
        int expected = 0;

// The query get count of rows which checks before the date
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + table + " WHERE (date < '" + date + "' or date is null)");
            resultSet.next();
            expected = resultSet.getInt(1); //expected value is set as result query
            statement.close();
        }catch (SQLException ex){
            System.out.println("Inside unit test: " + ex);
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    System.out.println("Inside unit test: " + ex);
                }
            }
        }
        UrlList urlList = SQLManager.getUrlList(date);//getting URL-list
        int actual = urlList.size();//actual value is set as the URL-list size
        assertEquals(expected, actual);//comparing two values
    }

    @Test
    public void update() {
        int expected = 100;
        int id = 1;
        int status = 0;

//getting confirmed id and your status
        try{
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(id), status FROM " + table);
            resultSet.next();
            id = resultSet.getInt(1);
            status = resultSet.getInt(2);
            statement.close();
        }catch (SQLException ex){
            System.out.println("Inside unit test: " + ex);
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    System.out.println("Inside unit test: " + ex);
                }
            }
        }

//Creating Status-list by adding equal rows with confirmed id.
        StatList statList = new StatList();
        for(int i = 0; i < expected; i++){//Size of the list is expected value.
            statList.add(new Statobj(id, status));
        }
        int actual = SQLManager.update(statList);//Update method getting actual value updated rows
        assertEquals(expected, actual);//comparing two values
    }
}
import java.io.File;
import java.util.Scanner;

public class Data {
    public static String getDate(){
        String string = "";
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("date")){
                    scanner.skip("date = ");
                    string = scanner.nextLine();
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
        System.out.println(e);
    }finally {
        if (scanner != null){
            scanner.close();
        }
    }
        return string;
    }
    public static String getUrl(){
        String string = "";
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("url")){
                    scanner.skip("url = ");
                    string = scanner.nextLine();
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return string;
    }
    public static String getLogin(){
        String string = "";
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("login")){
                    scanner.skip("login = ");
                    string = scanner.nextLine();
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return string;
    }
    public static String getPssword(){
        String string = "";
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("password")){
                    scanner.skip("password = ");
                    string = scanner.nextLine();
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return string;
    }
    public static String getTable(){
        String string = "";
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("table")){
                    scanner.skip("table = ");
                    string = scanner.nextLine();
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return string;
    }
    public static int getPoolsize(){
        int integer = 1;
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
            while(scanner.hasNextLine()){
                if (scanner.hasNext("poolsize")){
                    scanner.skip("poolsize = ");
                    integer = Integer.parseInt(scanner.nextLine());
                }else{
                    scanner.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return integer;
    }

}

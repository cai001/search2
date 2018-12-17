import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UrlThread implements Runnable{
    public Thread thread;
    private String name;
    private String url;
    private int id;
    private StatList statList;
    UrlThread(UrlList urlList, StatList statList){
        this.url = urlList.get(0).url;
        this.id = urlList.get(0).id;
        urlList.remove(0);
        this.statList = statList;
        thread = new Thread(this);
    }
    public void run(){
        HttpURLConnection hpCon = null;
        try{
            URL hp = new URL(url);
            hpCon = (HttpURLConnection) hp.openConnection();
            statList.add(new Statobj(id, hpCon.getResponseCode()));
        }catch (UnknownHostException uhe){
            statList.add(new Statobj(id, 600));
        }catch (ConnectException ce){
            statList.add(new Statobj(id, 601));
            System.out.println("Сеть перегружена");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            if(hpCon != null){
                hpCon.disconnect();
            }
        }

    }
}

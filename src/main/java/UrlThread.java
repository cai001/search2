import java.io.IOException;
import java.net.*;

public class UrlThread implements Runnable{
    public Thread thread;
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
            hpCon = (HttpURLConnection) hp.openConnection();//Try to connect
            statList.add(new Statobj(id, hpCon.getResponseCode()));//Making status list after getting link status

//With each exception, the link status takes a custom value equal to 600 or greater
        }catch (UnknownHostException e){
            statList.add(new Statobj(id, 600));//Link invalid status
            System.out.println(e);
        }catch (MalformedURLException e){
            statList.add(new Statobj(id, 601));//Invalid link construction
            System.out.println(e);
        }catch (ConnectException e){
            statList.add(new Statobj(id, 602));//Network overloaded
            System.out.println(e);
        }catch(IOException e){
            statList.add(new Statobj(id, 603));//Other exceptions
            System.out.println(e);
        }finally{
            if(hpCon != null){
               hpCon.disconnect();
            }
        }

    }
}

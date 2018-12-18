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
            hpCon = (HttpURLConnection) hp.openConnection();
            statList.add(new Statobj(id, hpCon.getResponseCode()));
        }catch (UnknownHostException e){
            statList.add(new Statobj(id, 600));
            System.out.println("По указанному URL, Host несуществует:\n" + e);
        }catch (MalformedURLException e){
            statList.add(new Statobj(id, 601));
            System.out.println("Ссылка URL задана неверно:\n" + e);
        }catch (ConnectException e){
            statList.add(new Statobj(id, 602));
            System.out.println("Сеть перегружена:\n" + e);
        }catch(IOException e){
            statList.add(new Statobj(id, 603));
            System.out.println("Непредвиденый обрыв связи:\n" + e);
        }finally{
            if(hpCon != null){
               hpCon.disconnect();
            }
        }

    }
}

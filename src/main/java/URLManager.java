import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class URLManager {
    private static int number = Data.getThreadslimit();//Limit thread number is set there
    public static StatList getStatList(UrlList urlList){
        ArrayList<Future> futures = new ArrayList<Future>();
        StatList statList = new StatList();
        ExecutorService service = Executors.newFixedThreadPool(number);

//UrlThread constructor removes one element from urlList in each cycle
        while(!urlList.isEmpty()){
            futures.add(service.submit(new UrlThread( urlList, statList)));//Each running thread is listed
        }

//If the thread is done, it's removed from the list
        while (!futures.isEmpty()){
            for(int i = 0; i < futures.size(); i++){
                if (futures.get(i).isDone()){
                    futures.remove(i);
                }
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
                System.out.println(ie);
            }
        }
        service.shutdown();//All threads shut down after done work
        return statList;
    }
}

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class URLManager {
    private static int number = 1000;
    public StatList getStatList(UrlList urlList){
        ArrayList<Future> futures = new ArrayList<Future>();
        StatList statList = new StatList();
        ExecutorService service = Executors.newFixedThreadPool(number);

        while(!urlList.isEmpty()){
            futures.add(service.submit(new UrlThread( urlList, statList)));
        }

        while (!futures.isEmpty()){
            for(int i = 0; i < futures.size(); i++){
                if (futures.get(i).isDone()){
                    futures.remove(i);
                }
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
                System.out.println("Ожидание неудачно:\n" + ie);
            }
        }
        service.shutdown();
        return statList;
    }
}

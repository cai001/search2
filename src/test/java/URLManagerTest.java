import org.junit.Test;

import static org.junit.Assert.*;

public class URLManagerTest {

    @Test
    public void getStatList() {
        int expected = 100;
        UrlList urlList = new UrlList();

//Creating URL list of expected size
        for(int i = 0; i < expected; i++){
            urlList.add(new Urlobj(i,"https://github.com"));
        }

//Getting status list of expected size
        StatList statList = URLManager.getStatList(urlList);
        int actual = statList.size();
        assertEquals(expected, actual);//comparing two values
    }
}
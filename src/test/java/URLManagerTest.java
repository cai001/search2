import org.junit.Test;

import static org.junit.Assert.*;

public class URLManagerTest {

    @Test
    public void getStatList() {
        int expected = 100;
        UrlList urlList = new UrlList();
        for(int i = 0; i < expected; i++){
            urlList.add(new Urlobj(i,"https://product-test.ru"));
        }
        StatList statList = URLManager.getStatList(urlList);
        int actual = statList.size();
        assertEquals(expected, actual);
    }
}
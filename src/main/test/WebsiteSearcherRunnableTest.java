package main.test;
import main.java.WebsiteSearcherRunnable;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WebsiteSearcherRunnableTest {

    @Test
    public void test_website_searcher() throws IOException{
        WebsiteSearcherRunnable runnable = new WebsiteSearcherRunnable("http://www.reddit.com","string_makes_no_sense");
        Assert.assertEquals(runnable.parseUrlData(), false);

        runnable = new WebsiteSearcherRunnable("http://www.cnn.com","cnn");
        Assert.assertEquals(runnable.parseUrlData(), false);
    }

}
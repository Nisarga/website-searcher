package main.java.websitesearcher;

import main.java.WebsiteSearcherRunnable;
import main.java.mulithreadedexecutors.MyThreadPoolImplementation;
import main.java.utils.UrlExtractor;
import main.java.utils.WebsiteInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Main driver class for Website Searcher application
 * Given a list of URLs in resources/input/urls.txt find if a search term exists in the content of this URL
 */
public class Application {
    static String searchTerm = "is";

    public static final int MAX_NUM_THREADS = 20;

    public static void main(String[] args) throws IOException, InterruptedException{
        InputStream inputStream =Application.class.getResourceAsStream("/urls.txt");

        List<WebsiteInformation> urlList = UrlExtractor.extractUrlFromFile(inputStream);
        System.out.println("Size of list is " + urlList.size());

        MyThreadPoolImplementation threadPool = new MyThreadPoolImplementation(urlList.size(), MAX_NUM_THREADS);


        long start = System.currentTimeMillis();
        long end = start + 60*1000; // 60 seconds * 1000 ms/sec
        while (System.currentTimeMillis() < end)
        {
            for(WebsiteInformation url : urlList) {
                Thread t = new Thread(new WebsiteSearcherRunnable(url.getUrl(),searchTerm));
                threadPool.submitTask(t);
            }
        }

        System.exit(1);
    }
}

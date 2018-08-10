package main.java.websitesearcher;

import main.java.WebsiteSearcherRunnable;
import main.java.mulithreadedexecutors.MyThreadPoolImplementation;
import main.java.utils.CreateUtilities;
import main.java.utils.WebsiteInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Main driver class for Website Searcher application
 * Given a list of URLs in resources/urls.txt find if a search term exists in the content of this URL
 */
public class Application {
    static String searchTerm = "is";

    public static final int MAX_NUM_THREADS = 20;

    public static void main(String[] args) throws IOException, InterruptedException{
        InputStream inputStream =Application.class.getResourceAsStream("/urls.txt");

        List<WebsiteInformation> urlList = CreateUtilities.extractUrlFromFile(inputStream);
        CreateUtilities.initializeResources();

        MyThreadPoolImplementation threadPool = new MyThreadPoolImplementation(urlList.size(), MAX_NUM_THREADS);


        for(WebsiteInformation url : urlList) {
            Thread t = new Thread(new WebsiteSearcherRunnable(url.getUrl(),searchTerm));
            threadPool.submitTask(t);
        }

    }
}

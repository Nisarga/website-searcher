package main.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple utility class which goes through the URL list and creates a website object
 * Returns a list of website objects
 *
 */
public class UrlExtractor {
    public static List<WebsiteInformation> extractUrlFromFile(InputStream inputStream) throws IOException {
        List<WebsiteInformation> urlList = new ArrayList<>();
        //InputStream inputStream1 = UrlExtractor.class.getResourceAsStream("urls.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = br.readLine()) != null) {
                if(skipFirstLine){
                    skipFirstLine = false;
                    continue;
                }
                String[] urlParser  = line.split(",");
                int rank = Integer.parseInt(urlParser[0]);
                String url = "http://www." + urlParser[1].substring(1 ,urlParser[1].length() - 2);
                int linkingRootDomain = Integer.parseInt(urlParser[2]);
                int externalLink = Integer.parseInt(urlParser[3]);
                double mozRank = Double.parseDouble(urlParser[4]);
                double mozTrust = Double.parseDouble(urlParser[5]);
                WebsiteInformation websiteObject = new WebsiteInformation(rank,url,linkingRootDomain,externalLink,mozRank,mozTrust);
                urlList.add(websiteObject);
            }
        }
        inputStream.close();
        return urlList;
    }
}

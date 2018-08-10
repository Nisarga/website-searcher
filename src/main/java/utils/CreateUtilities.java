package main.java.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Simple utility class which goes through the URL list and creates a website object
 * Also initializes some resources
 */

public class CreateUtilities {
    private final static Logger LOGGER = Logger.getLogger(CreateUtilities.class.getName());

    public static List<WebsiteInformation> extractUrlFromFile(InputStream inputStream) throws IOException {
        List<WebsiteInformation> urlList = new ArrayList<>();
        //InputStream inputStream1 = CreateUtilities.class.getResourceAsStream("urls.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
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

    public static void initializeResources(){
        try{
            FileWriter fileWriter = new FileWriter("myfile.txt", false);
            fileWriter.close();
        }
        catch(IOException ex){
            LOGGER.warning("Unable to create output file" + ex.toString());
        }
    }
}

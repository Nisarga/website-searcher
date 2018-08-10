package main.java;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

public class WebsiteSearcherRunnable implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(WebsiteSearcherRunnable.class.getName());
    private static long currentTime;
    private volatile boolean stop = false;

    String url;
    String searchTerm;

    public WebsiteSearcherRunnable(String url, String searchTerm) {
        this.url = url;
        this.searchTerm = searchTerm;
    }

    @Override
    public void run() {

        try {
            parseUrlData();
        } catch (MalformedURLException e) {
            LOGGER.warning("Exception occurred" + e);
        } catch (IOException ex) {
            LOGGER.warning("Exception occurred" + ex);
        }
    }

    public boolean parseUrlData() throws IOException {
        URL url = new URL(this.url);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            if (inputLine!=null && Arrays.asList(inputLine.split(" ")).contains(this.searchTerm)) {
                System.out.println("Writing to file when url is " + url);
                writeToFile(url);
                return true;
            }
        in.close();

        return false;
    }

    synchronized public void writeToFile(URL url) {
        System.out.println("Writing to file");
        try (FileWriter fw = new FileWriter("myfile.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(url.toString());
        } catch (IOException e) {
            LOGGER.warning("Unable to write to output file" + e.toString());
        }

    }
}

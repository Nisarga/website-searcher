package main.java;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * This class implements the Java Runnable interface which creates an instance to be run by a thread
 * The run method here extracts html content from the url and runs a rudimentary search of a word in it
 * The "Search" here is just a simple word search after a sentence is split by a space
 */
public class WebsiteSearcherRunnable implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(WebsiteSearcherRunnable.class.getName());

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
                writeToFile(url);
                return true;
            }
        in.close();

        return false;
    }

    synchronized public void writeToFile(URL url) {
        try (FileWriter fw = new FileWriter("results.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(url.toString());
        } catch (IOException e) {
            LOGGER.warning("Unable to write to output file" + e.toString());
        }

    }
}

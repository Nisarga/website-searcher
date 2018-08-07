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
        /*HttpURLConnection con;

        try {
            HttpURLConnection.setFollowRedirects(false);
            con = (HttpURLConnection) new URL(this.url).openConnection();
            con.setRequestMethod("HEAD");

            con.setConnectTimeout(1000); //set timeout to 5 seconds
            con.setReadTimeout(5000);
        } catch (java.net.SocketTimeoutException e) {
            return false;
        } catch (java.io.IOException e) {
            return false;
        }*/


        /*if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("URL is " + this.url);
            String inputLine;
            while ((inputLine = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)).readLine()) != null) {
                System.out.println("Reading line");
                if (Arrays.asList(inputLine.split(" ")).contains(this.searchTerm)) {
                    writeToFile(url);
                    return true;
                }
            }
        //}

        return false;*/


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

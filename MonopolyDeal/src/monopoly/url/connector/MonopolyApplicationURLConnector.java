/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.url.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import monopoly.constants.MonopolyDealConstants;

/**
 *
 * @author Reena
 */
public class MonopolyApplicationURLConnector {

    public MonopolyApplicationURLConnector() {
    }

    private String buildURLFromComponent(String urlComponent) {
        StringBuilder urlBuilder = new StringBuilder("");
        if (System.getProperty(MonopolyDealConstants.RUNNING_ENVIRONMENT).equalsIgnoreCase(MonopolyDealConstants.WEB_RUNNING_ENVIRONMENT)) {
            urlBuilder.append(MonopolyDealConstants.HEROKU_URL);
        } else {
            urlBuilder.append(MonopolyDealConstants.LOCAL_HOSTNAME);
        }
        urlBuilder.append(urlComponent);
        return urlBuilder.toString();
    }

    public String callURL(String myURLComponent) {
        String myURL = buildURLFromComponent(myURLComponent);
        System.out.println("Requeted URL: " + myURL);
        StringBuilder sb = new StringBuilder(MonopolyDealConstants.EMPTY_STRING);
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("connection error");
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return sb.toString();
    }

    public String callURL(String myURLComponent, String urlParameters) {
        String myURL = buildURLFromComponent(myURLComponent);
        System.out.println("Requeted URL: " + myURL);

//        urlParameters = "param1=a&param2=b&param3=c";

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        URL url = null;
        try {
            url = new URL(myURL);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        StringBuilder serverOutput = new StringBuilder();
        InputStreamReader in = null;

        if (connection != null) {
            connection.setReadTimeout(60 * 1000);
        }
        try {
            if (connection != null && connection.getInputStream() != null) {
                in = new InputStreamReader(connection.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        serverOutput.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(MonopolyApplicationURLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverOutput.toString();
    }
}

package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Donnie on 2017/2/20.
 */
@Component
public class UrlUtil {
    private static Logger logger = LoggerFactory.getLogger(UrlUtil.class);
    public static String urlGet (String urlStr) {
        StringBuilder json = new StringBuilder();
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
        } catch (MalformedURLException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return json.toString();
    }
    public String urlPost(String urlStr, String param) {
        StringBuilder response = new StringBuilder();
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            //post method
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return response.toString();
    }
}

package com.phoenixx.bot.handlers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:55 PM
 **/
public class APIHandler
{
    //TODO Use updated system for get requests
    public static String sendGet(String url) {

        try {
            String requestURL = "https://dmoj.ca/api/" + url;

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(requestURL);

            HttpResponse response = client.execute(request);

            System.out.println("\nSending 'GET' request to URL : " + requestURL);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode() != 404)
            {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result.toString());

                return result.toString();
            } else {
                return "**FAILED TO GET DATA**";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "**FAILED TO GET DATA**";
    }
}

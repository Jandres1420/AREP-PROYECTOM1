package edu.ecu.co.AirApp.utils;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MultipleThreads implements Runnable{
    private final String API_KEY ="3ffe344920a24666a225f0a34c78b585";
    private String url = "https://api.breezometer.com/air-quality/v2/forecast/hourly?lat=";
    private String latitude,longitude,all;
    private  Object[] data;
    private final String USER_AGENT = "Mozilla/5.0";


    public MultipleThreads(String latitude,String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public void run() {
        url+=latitude+"&lon="+longitude+"&key="+API_KEY+"&hours=3";
        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        con.setRequestProperty("User-Agent", USER_AGENT);
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = 0;
        try {
            responseCode = con.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String inputLine;
            StringBuffer response = new StringBuffer();

            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                response.append(inputLine);
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // print result
            all= response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
    }

    public String getAll(){
        return all;
    }
}

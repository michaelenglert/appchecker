package com.appdynamics.api.appcheck;

import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("StatementWithEmptyBody")
class Rest {
    static String doGet(String user, String account, String password, URL url) throws Exception{
        String auth = user + "@" + account + ":" + password;
        String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        String response = "";
        for (String line; (line = br.readLine()) != null; response += line);
        conn.disconnect();
        return response;
    }

    static void doPost(String user, String account, String password, URL url) throws Exception {
        String auth = user + "@" + account + ":" + password;
        String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
        System.out.println(conn.getResponseMessage());
        conn.disconnect();
    }
}

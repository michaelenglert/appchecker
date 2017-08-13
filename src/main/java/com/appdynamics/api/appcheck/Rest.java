package com.appdynamics.api.appcheck;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.codec.binary.Base64;


public class Rest {
    static ClientResponse doGet(String user, String account, String password, String url) throws Exception{
        String auth = user + "@" + account + ":" + password;
        String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));

        ClientResponse response = getWebResource(url).header("Authorization", "Basic " + encodedAuth).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Error Code: " + response.getStatus());
        }

        return response;
    }

    static void doPost(String user, String account, String password, String url) throws Exception{

    }

    private static WebResource getWebResource(String url) throws Exception{
        Client client = Client.create();

        if (url.startsWith("https")) {
            client = new SslClientHelper().hostIgnoringClient();
        }

        return client.resource(url);
    }
}

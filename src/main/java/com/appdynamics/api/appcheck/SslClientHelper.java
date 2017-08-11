package com.appdynamics.api.appcheck;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.util.Map;

public class SslClientHelper {
    Client hostIgnoringClient() throws Exception{
        SSLContext sslcontext = SSLContext.getInstance( "TLS" );
        sslcontext.init( null, null, null );
        DefaultClientConfig config = new DefaultClientConfig();
        Map<String, Object> properties = config.getProperties();
        HTTPSProperties httpsProperties = new HTTPSProperties(
                new HostnameVerifier()
                {
                    @Override
                    public boolean verify( String s, SSLSession sslSession )
                    {
                        return true;
                    }
                }, sslcontext
        );
        properties.put( HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, httpsProperties );
        return Client.create( config );

    }
}

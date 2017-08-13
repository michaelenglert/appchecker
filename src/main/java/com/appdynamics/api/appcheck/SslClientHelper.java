package com.appdynamics.api.appcheck;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import javax.net.ssl.SSLContext;
import java.util.Map;

class SslClientHelper {
    Client hostIgnoringClient() throws Exception{
        SSLContext sslcontext = SSLContext.getInstance( "TLS" );
        sslcontext.init( null, null, null );
        DefaultClientConfig config = new DefaultClientConfig();
        Map<String, Object> properties = config.getProperties();
        HTTPSProperties httpsProperties = new HTTPSProperties(
                (s, sslSession) -> true, sslcontext
        );
        properties.put( HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, httpsProperties );
        return Client.create( config );

    }
}

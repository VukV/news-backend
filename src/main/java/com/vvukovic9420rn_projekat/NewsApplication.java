package com.vvukovic9420rn_projekat;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class NewsApplication extends ResourceConfig {

    public NewsApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                //TODO
            }
        };
        register(binder);

        packages("com.vvukovic9420rn_projekat");
    }
}

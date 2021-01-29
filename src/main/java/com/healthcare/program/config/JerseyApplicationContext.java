package com.healthcare.program.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.healthcare.program.rest.HealthcareProgramResourceImpl;


@Configuration
@ApplicationPath("v1")
public class JerseyApplicationContext extends ResourceConfig {

    public JerseyApplicationContext() {

        register(HealthcareProgramResourceImpl.class);

    }
}

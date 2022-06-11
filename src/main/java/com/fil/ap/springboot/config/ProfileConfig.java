package com.fil.ap.springboot.config;

import com.fil.ap.springboot.constants.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfig {

    @Autowired
    private ApplicationContext context;

    public String getActiveProfile() {

        String profile = Profiles.DEVELOPING.getName();

        if(context != null) {

            profile = context.getEnvironment().getActiveProfiles()[0];
        }

        return profile;
    }
}

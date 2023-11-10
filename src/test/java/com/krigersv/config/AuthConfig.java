package com.krigersv.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("userName")
    String getUserName();

    @Key("accessKey")
    String getAccessKey();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("framework")
    String getFramework();
}

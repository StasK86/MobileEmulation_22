package com.krigersv.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:android.properties"
})
public interface AndroidMobileConfig extends Config {

    @Key("platformName")
    String getPlatformName();

    @Key("deviceName")
    String getDeviceName();

    @Key("osVersion")
    String getVersion();

    @Key("app")
    String getApp();

    @Key("projectName")
    String getProject();

    @Key("buildName")
    String getBuild();

    @Key("testName")
    String getTestName();
}

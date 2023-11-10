package com.krigersv.config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:emulator.properties"
})
public interface AndroidLocalConfig extends Config {
    @Key("platformVersion")
    String getPlatformVersion();

    @Key("platformName")
    String getPlatformName();

    @Key("deviceName")
    String getDeviceName();
}

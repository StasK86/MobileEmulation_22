package com.krigersv.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.krigersv.config.AuthConfig;
import com.krigersv.config.AndroidMobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.getProperties;

public class BrowserstackDriver implements WebDriverProvider {
    protected static AndroidMobileConfig config = ConfigFactory.create(AndroidMobileConfig.class, getProperties());
    protected static AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, getProperties());

    @Nonnull
    @Override

    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", authConfig.getUserName());
        mutableCapabilities.setCapability("browserstack.key", authConfig.getAccessKey());
        mutableCapabilities.setCapability("framework", authConfig.getFramework());

        mutableCapabilities.setCapability("app", config.getApp());
        mutableCapabilities.setCapability("deviceName", config.getDeviceName());
        mutableCapabilities.setCapability("osVersion", config.getVersion());

        mutableCapabilities.setCapability("platformName", config.getPlatformName());
        mutableCapabilities.setCapability("projectName", config.getProject());
        mutableCapabilities.setCapability("buildName", config.getBuild());
        mutableCapabilities.setCapability("testName", config.getTestName());

        try {
            return new RemoteWebDriver(
                    new URL(authConfig.getRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

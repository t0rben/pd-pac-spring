package com.prodyna.pac.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "prodyna.pac")
public class ProdynaHealthProperties {

    @Min(0)
    private int retryLimit;

    @NotNull
    private String url;

    private boolean stopApplication;

    public int getRetryLimit() {

        return retryLimit;
    }

    public void setRetryLimit(int retryLimit) {

        this.retryLimit = retryLimit;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public boolean isStopApplication() {

        return stopApplication;
    }

    public void setStopApplication(boolean stopApplication) {

        this.stopApplication = stopApplication;
    }
}

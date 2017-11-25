package com.shortenurl.api.controllers.responses.data;

/**
 * Created by iljun on 2017-11-23.
 */
public class RedirectResponseData {
    private static final String TAG = RedirectResponseData.class.getSimpleName();

    private String originalUrl;

    public RedirectResponseData(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public RedirectResponseData(){

    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}

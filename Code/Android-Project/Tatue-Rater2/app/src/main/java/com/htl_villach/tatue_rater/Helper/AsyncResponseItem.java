package com.htl_villach.tatue_rater.Helper;

/**
 * Created by Jonas on 15.03.2016.
 */
public class AsyncResponseItem {
    private String response;
    private AsyncResponseType type;

    public AsyncResponseItem(String response, AsyncResponseType type) {
        this.response = response;
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public AsyncResponseType getType() {
        return type;
    }

    public void setType(AsyncResponseType type) {
        this.type = type;
    }
}

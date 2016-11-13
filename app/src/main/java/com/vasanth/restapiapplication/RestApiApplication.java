package com.vasanth.restapiapplication;

import android.app.Application;

import com.vasanth.restapiapplication.httprestapi.HttpClientApi;
import com.vasanth.restapiapplication.httprestapi.volley.VolleyHttpRestApiImpl;

/**
 * Rest Api Application.
 * <p>
 * 1. Responsibility.
 * 1.a. Application class.
 *
 * @author Vasanth
 */
public class RestApiApplication extends Application {

    private HttpClientApi mHttpRestApi;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeHttpClient();
    }

    /**
     * Used to initialize HTTP Client.
     */
    private void initializeHttpClient() {
        mHttpRestApi = VolleyHttpRestApiImpl.getInstance(this);
    }

    /**
     * Used to get HTTP client api to perform rest api calls.
     *
     * @return HTTP client api.
     */
    public HttpClientApi getHttpClientApi() {
        return mHttpRestApi;
    }
}

package com.vasanth.restapiapplication.httprestapi.volley;

import android.content.Context;

import com.android.volley.Request;
import com.vasanth.restapiapplication.httprestapi.HttpClientApi;

import java.util.Map;

/**
 * Volley Http Rest Api Implementation.
 * <p>
 * 1. Responsibility.
 * 1.a. {@link HttpClientApi} implementation using Volley Library.
 *
 * @author Vasanth
 */
public class VolleyHttpRestApiImpl implements HttpClientApi {

    private static VolleyHttpRestApiImpl mInstance;
    private VolleySingleton volleySingleton;

    /**
     * Constructor.
     *
     * @param context Context.
     */
    private VolleyHttpRestApiImpl(final Context context) {
        volleySingleton = VolleySingleton.getInstance(context);
    }

    /**
     * Used to singleton instance of VolleySingleton.
     *
     * @param context Context.
     * @return Singleton instance of VolleySingleton.
     */
    public static synchronized VolleyHttpRestApiImpl getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHttpRestApiImpl(context);
        }
        return mInstance;
    }

    /**
     * Used to make "HTTP GET REQUEST" & get response using callback.
     *
     * @param identifier  Unique string to identify individual request.
     * @param headers     Request headers.
     * @param url         Request Url.
     * @param shouldCache whether or not responses to this request should be cached.
     * @param listener    Listener used to get callback on request completes.
     */
    @Override
    public void getRequest(String identifier, Map<String, String> headers, String url, final boolean shouldCache, HttpResponseListener listener) {
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(identifier, Request.Method.GET, headers, url, shouldCache, listener);
        volleySingleton.addToRequestQueue(volleyStringRequest);
    }

    /**
     * Used to make "HTTP POST REQUEST" & get response using callback.
     *
     * @param identifier      Unique string to identify individual request.
     * @param headers         Request headers.
     * @param url             Request Url.
     * @param body            Request Body.
     * @param bodyContentType Request Body Content Type.
     * @param shouldCache     whether or not responses to this request should be cached.
     * @param listener        Listener used to get callback on request completes.
     */
    @Override
    public void postRequest(String identifier, Map<String, String> headers, String url, String body, String bodyContentType,
                            final boolean shouldCache, HttpResponseListener listener) {
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(identifier, Request.Method.POST, headers, url, body, bodyContentType,
                shouldCache, listener);
        volleySingleton.addToRequestQueue(volleyStringRequest);
    }

    /**
     * Used to make "HTTP PUT REQUEST" & get response using callback.
     *
     * @param identifier      Unique string to identify individual request.
     * @param headers         Request headers.
     * @param url             Request Url.
     * @param body            Request Body.
     * @param bodyContentType Request Body Content Type.
     * @param shouldCache     whether or not responses to this request should be cached.
     * @param listener        Listener used to get callback on request completes.
     */
    @Override
    public void putRequest(String identifier, Map<String, String> headers, String url, String body, String bodyContentType,
                           final boolean shouldCache, HttpResponseListener listener) {
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(identifier, Request.Method.PUT, headers, url, body, bodyContentType,
                shouldCache, listener);
        volleySingleton.addToRequestQueue(volleyStringRequest);
    }

    /**
     * Used to make "HTTP DELETE REQUEST" & get response using callback.
     *
     * @param identifier  Unique string to identify individual request.
     * @param headers     Request headers.
     * @param url         Request Url.
     * @param shouldCache whether or not responses to this request should be cached.
     * @param listener    Listener used to get callback on request completes.
     */
    @Override
    public void deleteRequest(String identifier, Map<String, String> headers, String url, final boolean shouldCache, HttpResponseListener listener) {
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(identifier, Request.Method.DELETE, headers, url, shouldCache, listener);
        volleySingleton.addToRequestQueue(volleyStringRequest);
    }

    /**
     * Used to cancel the request.
     *
     * @param identifier Unique string to identify the request & cancel it.
     */
    @Override
    public void cancelRequest(String identifier) {
        volleySingleton.getRequestQueue().cancelAll(identifier);
    }
}

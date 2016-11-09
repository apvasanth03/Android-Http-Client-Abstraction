package com.vasanth.restapiapplication.httprestapi;

import java.util.Map;

/**
 * Http Client Api.
 * <p>
 * 1. Responsibility.
 * 1.a. Interface defines Common HTTP Methods.
 * 1.b. Hence we need to implement this interface using some HTTP client (like Volley, Retrofit etc) and
 * provide functionality for the HTTP methods.
 * 1.c. Inside our application we will only refer this interface for any HTTPApi calls there by abstracting
 * the third party HTTP client implementation.
 *
 * @author Vasanth
 */
public interface HttpClientApi {

    // The Default Socket timeout in milliseconds
    int DEFAULT_REQUEST_SOCKET_TIMEOUT_MS = 25 * 100;

    // The Default number of retries
    int DEFAULT_REQUEST_MAX_RETRIES = 0;

    // The Default backoff multiplier - A multiplier which is used to determine exponential time set to socket for every retry attempt.
    float DEFAULT_REQUEST_BACKOFF_MULTIPLIER = 1f;

    // Use this bodyContType If - The body of the HTTP message sent to the server is essentially one giant query string --
    // name/value pairs are separated by the ampersand (&), and names are separated from values by the equal symbal (=).
    String BODY_CONTENT_TYPE_DEFAULT = "application/x-www-form-urlencoded; charset=UTF-8";

    // Use this bodyContType If - THe body of the HTTP message sent to the server is a json.
    String BODY_CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    /**
     * HTTP Response Listener.
     * <p>
     * 1. Responsibility.
     * 1.a. Interface use to send callback once request completes.
     */
    interface HttpResponseListener {

        // Error code indicates network error (like NoConnectivity & other networkErrors).
        int ERROR_CODE_NETWORK_ERROR = 1001;

        // Error code indicated request time out.
        int ERROR_CODE_TIME_OUT_ERROR = 1002;

        // Error code indicated some random unknown error (like BadUrl etc)
        int ERROR_CODE_UNKNOWN_ERROR = 1002;

        /**
         * Gets called on request success.
         *
         * @param response Response.
         */
        void onSuccessResponse(String response);

        /**
         * Gets called on request failure.
         *
         * @param errorCode Will get one of "ERROR_CODE_NETWORK_ERROR" OR "ERROR_CODE_TIME_OUT_ERROR" OR
         *                  "Common Http Error Codes"
         */
        void onErrorResponse(int errorCode);
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
    void getRequest(final String identifier, final Map<String, String> headers, final String url, final boolean shouldCache, final HttpResponseListener listener);

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
    void postRequest(final String identifier, final Map<String, String> headers, final String url, final String body, final String bodyContentType,
                     final boolean shouldCache, final HttpResponseListener listener);

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
    void putRequest(final String identifier, final Map<String, String> headers, final String url, final String body, final String bodyContentType,
                    final boolean shouldCache, final HttpResponseListener listener);

    /**
     * Used to make "HTTP DELETE REQUEST" & get response using callback.
     *
     * @param identifier  Unique string to identify individual request.
     * @param headers     Request headers.
     * @param url         Request Url.
     * @param shouldCache whether or not responses to this request should be cached.
     * @param listener    Listener used to get callback on request completes.
     */
    void deleteRequest(final String identifier, final Map<String, String> headers, final String url, final boolean shouldCache, final HttpResponseListener listener);

    /**
     * Used to cancel the request.
     *
     * @param identifier Unique string to identify the request & cancel it.
     */
    void cancelRequest(final String identifier);


}

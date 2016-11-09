package com.vasanth.restapiapplication.httprestapi.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vasanth.restapiapplication.httprestapi.HttpClientApi;

import java.util.Map;

/**
 * Volley String Request.
 * <p>
 * 1. Responsibility.
 * 1.a. Class responsible for retrieving the response body at a given URL as a String.
 *
 * @author Vasanth
 */
class VolleyStringRequest extends StringRequest {

    private Map<String, String> mHeaders;
    private String mBody;
    private String mBodyContentType;

    /**
     * Constructor.
     *
     * @param identifier  Unique string to identify individual request.
     * @param method      HTTP Request Method.
     * @param headers     Request Headers.
     * @param url         Request Url.
     * @param shouldCache whether or not responses to this request should be cached.
     * @param listener    Listener used to get callback on request completes.
     */
    VolleyStringRequest(final String identifier, final int method, final Map<String, String> headers, final String url,
                        final boolean shouldCache, final HttpClientApi.HttpResponseListener listener) {
        super(method, url, new ResponseSuccessListener(listener), new ResponseErrorListener(listener));

        setTag(identifier);
        setShouldCache(shouldCache);
        setRetryPolicy(new DefaultRetryPolicy(HttpClientApi.DEFAULT_REQUEST_SOCKET_TIMEOUT_MS, HttpClientApi.DEFAULT_REQUEST_MAX_RETRIES, HttpClientApi.DEFAULT_REQUEST_BACKOFF_MULTIPLIER));

        this.mHeaders = headers;
    }

    /**
     * Constructor.
     *
     * @param identifier      Unique string to identify individual request.
     * @param method          HTTP Request Method.
     * @param headers         Request Headers.
     * @param url             Request Url.
     * @param body            Request Body.
     * @param bodyContentType Request Body Content Type.
     * @param shouldCache     whether or not responses to this request should be cached.
     * @param listener        Listener used to get callback on request completes.
     */
    VolleyStringRequest(final String identifier, final int method, final Map<String, String> headers, final String url, final String body,
                        final String bodyContentType, final boolean shouldCache, final HttpClientApi.HttpResponseListener listener) {
        this(identifier, method, headers, url, shouldCache, listener);

        this.mBody = body;
        this.mBodyContentType = bodyContentType;
    }

    /**
     * Returns a list of extra HTTP headers to go along with this request.
     *
     * @return Headers.
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return (mHeaders != null) ? mHeaders : super.getHeaders();
    }

    /**
     * Used to get request body.
     *
     * @return Request body.
     * @throws AuthFailureError
     */
    @Override
    public byte[] getBody() throws AuthFailureError {
        return (mBody != null) ? mBody.getBytes() : super.getBody();
    }

    /**
     * Used to get request body content type.
     *
     * @return Request body content type.
     */
    @Override
    public String getBodyContentType() {
        return (mBodyContentType != null) ? mBodyContentType : super.getBodyContentType();
    }

    /**
     * Response Success Listener.
     * <p>
     * 1. Responsibility.
     * 1.a. Listener gets notified on success of request.
     */
    private static class ResponseSuccessListener implements Response.Listener<String> {

        private HttpClientApi.HttpResponseListener mListener;

        /**
         * Constructor.
         *
         * @param listener Listener used to send callback on resuest success.
         */
        ResponseSuccessListener(final HttpClientApi.HttpResponseListener listener) {
            this.mListener = listener;
        }

        /**
         * Gets called on success response.
         *
         * @param response Response.
         */
        @Override
        public void onResponse(final String response) {
            if (mListener != null) {
                mListener.onSuccessResponse(response);
            }
        }
    }

    /**
     * Response Error Listener.
     * <p>
     * 1. Responsibility.
     * 1.a. Listener gets notified on failure of request.
     */
    private static class ResponseErrorListener implements Response.ErrorListener {

        private HttpClientApi.HttpResponseListener mListener;

        /**
         * Constructor.
         *
         * @param listener Listener used to send callback on request failure.
         */
        ResponseErrorListener(final HttpClientApi.HttpResponseListener listener) {
            this.mListener = listener;
        }

        /**
         * Gets called on error response.
         *
         * @param error Error.
         */
        @Override
        public void onErrorResponse(final VolleyError error) {
            if (mListener != null) {
                int errorCode;
                if (error instanceof NetworkError) {
                    errorCode = HttpClientApi.HttpResponseListener.ERROR_CODE_NETWORK_ERROR;
                } else if (error instanceof TimeoutError) {
                    errorCode = HttpClientApi.HttpResponseListener.ERROR_CODE_TIME_OUT_ERROR;
                } else {
                    errorCode = (error.networkResponse != null) ? error.networkResponse.statusCode : HttpClientApi.HttpResponseListener.ERROR_CODE_UNKNOWN_ERROR;
                }

                mListener.onErrorResponse(errorCode);
            }
        }
    }
}

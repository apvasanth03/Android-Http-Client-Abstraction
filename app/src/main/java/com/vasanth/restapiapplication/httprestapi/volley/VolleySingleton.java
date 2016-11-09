package com.vasanth.restapiapplication.httprestapi.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Volley Singleton.
 * <p>
 * 1. Responsibility.
 * 1.a. Singleton class used to provide single instance volley components.
 * 1.b. Provides "Request Queue" & allows as to add "Request" to the queue.
 *
 * @author Vasanth
 */
class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;

    /**
     * Constructor.
     *
     * @param context Application Context.
     */
    private VolleySingleton(final Context context) {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    /**
     * Used to singleton instance of VolleySingleton.
     *
     * @param context Context.
     * @return Singleton instance of VolleySingleton.
     */
    static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    /**
     * Used to get volley request queue.
     *
     * @return Volley request queue.
     */
    RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * Used to add the request to the request queue.
     *
     * @param request Request to be added to the queue.
     */
    <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}

package com.vasanth.restapiapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vasanth.restapiapplication.httprestapi.HttpClientApi;
import com.vasanth.restapiapplication.httprestapi.volley.VolleyHttpRestApiImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main Activity.
 * <p>
 * 1. Responsibility.
 * 1.a. Activity used to test HttpRestApi.
 *
 * @author Vasanth
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final String HOST_URL = "http://jsonplaceholder.typicode.com";

    private Button mButtonTestGetRequest;
    private Button mButtonTestPostRequest;
    private Button mButtonTestPutRequest;
    private Button mButtonTestDeleteRequest;
    private ProgressDialog mProgressDialog;

    private HttpClientApi mHttpRestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTestGetRequest = (Button) findViewById(R.id.button_activityMain_testGetRequest);
        mButtonTestPostRequest = (Button) findViewById(R.id.button_activityMain_testPostRequest);
        mButtonTestPutRequest = (Button) findViewById(R.id.button_activityMain_testPutRequest);
        mButtonTestDeleteRequest = (Button) findViewById(R.id.button_activityMain_testDeleteRequest);

        mButtonTestGetRequest.setOnClickListener(this);
        mButtonTestPostRequest.setOnClickListener(this);
        mButtonTestPutRequest.setOnClickListener(this);
        mButtonTestDeleteRequest.setOnClickListener(this);

        mHttpRestApi = VolleyHttpRestApiImpl.getInstance(this);
    }

    /**
     * View.OnClickListener.
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_activityMain_testGetRequest) {
            testGetRequest();
        } else if (view.getId() == R.id.button_activityMain_testPostRequest) {
            testPostRequest();
        } else if (view.getId() == R.id.button_activityMain_testPutRequest) {
            testPutRequest();
        } else if (view.getId() == R.id.button_activityMain_testDeleteRequest) {
            testDeleteRequest();
        }
    }

    /**
     * Used to test "Get Request".
     */
    private void testGetRequest() {
        String url = HOST_URL + "/posts/1";
        showProgressDialog();
        mHttpRestApi.getRequest(TAG, null, url, false, new HttpClientApi.HttpResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Success : " + response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(int errorCode) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Error : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Used to test "Post Request".
     */
    private void testPostRequest() {
        String url = HOST_URL + "/posts";
        JSONObject body = new JSONObject();
        try {
            body.put("title", "foo");
            body.put("body", "bar");
            body.put("userId", 1);
        } catch (JSONException exp) {
            exp.printStackTrace();
        }
        showProgressDialog();
        mHttpRestApi.postRequest(TAG, null, url, body.toString(), HttpClientApi.BODY_CONTENT_TYPE_JSON, false, new HttpClientApi.HttpResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Success : " + response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(int errorCode) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Error : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Used to test "Post Request".
     */
    private void testPutRequest() {
        String url = HOST_URL + "/posts/1";
        JSONObject body = new JSONObject();
        try {
            body.put("id", 1);
            body.put("title", "foo");
            body.put("body", "bar");
            body.put("userId", 1);
        } catch (JSONException exp) {
            exp.printStackTrace();
        }
        showProgressDialog();
        mHttpRestApi.putRequest(TAG, null, url, body.toString(), HttpClientApi.BODY_CONTENT_TYPE_JSON, false, new HttpClientApi.HttpResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Success : " + response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(int errorCode) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Error : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Used to test "Delete Request".
     */
    private void testDeleteRequest() {
        String url = HOST_URL + "/posts/1";
        showProgressDialog();
        mHttpRestApi.deleteRequest(TAG, null, url, false, new HttpClientApi.HttpResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Success : " + response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(int errorCode) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this, "Error : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Used to show progress dialog.
     */
    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * Used to hide progress dialog.
     */
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

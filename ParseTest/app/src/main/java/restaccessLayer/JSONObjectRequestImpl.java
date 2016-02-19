package restaccessLayer;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 1/9/2016.
 */
public class JSONObjectRequestImpl extends JsonObjectRequest {

    public JSONObjectRequestImpl(int method, String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Accept", "application/json");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        RetryPolicy rp = new RetryPolicy() {

            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 4;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                Log.i("MyApplication", "Retry is failed");
            }
        };
        return rp;
    }
}

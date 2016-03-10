package restaccessLayer;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Alex on 1/10/2016.
 */
public class ResponseEventWrapper<T> implements Response.Listener<Event[]>, Response.ErrorListener {

    private RestCallback<T> callBack;
    public ResponseEventWrapper(RestCallback<T> cal) {
        callBack = cal;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    //public void onResponse(Event[] response) {
    public void onResponse(Event[] response) {
        for(Event e : response) {
            Log.i("All_Gadera", e.toString());
        }
    }
}

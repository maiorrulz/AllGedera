package restaccessLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Alex on 1/9/2016.
 */
public class RestAccessLayer /*implements Response.Listener<Event[]>, Response.ErrorListener*/ {

    private static RestAccessLayer dataAccess;
    private RequestQueue rQueue;
    private Map<String, String> properties;

    private RestAccessLayer() {
    };

    public static RestAccessLayer getInstance(Context context, String pathToConfigFile) throws IOException {
        if (dataAccess == null) {
            synchronized (RestAccessLayer.class) {
                if (dataAccess == null) {
                    dataAccess = new RestAccessLayer();
                    if(context == null)
                    {
                        throw new IOException("Context is null.Provide correct context");
                    }
                    if(pathToConfigFile.isEmpty() || pathToConfigFile == null)
                    {
                        throw new IOException("Path to file empty or null.Provide correct path");
                    }
                    dataAccess.rQueue =Volley.newRequestQueue(context);


                    PropertiesInitilizator pi = new PropertiesInitilizator(pathToConfigFile);
                    dataAccess.properties = pi.initPropertyInfo();
                    if(dataAccess.properties == null) {
                        System.err.println("Error initializing properties");
                        throw new IOException("Failed to init properties from file " + pathToConfigFile);
                    }

                }
            }
        }
        return dataAccess;
    }





    public void runJsonRequestGetEvent(final RestCallback.OnResponseSuccess ors, final RestCallback.OnResponseFailure orf) throws IOException {
        String url = properties.get("ip") + "/"+properties.get("restApiPath") + "/" + properties.get("eventPath");

        Log.d("All_gedera:url", url);
        final Request jsonRequest = new GsonRequest<> (url, Event[].class, new Response.Listener<Event[]>(){
            @Override
            public void onResponse(Event[] response) {
                for(Event e : response) {
                    Log.i("All_Gadera", e.toString());
                }

                Log.e("matan", "response:" + response[0]);
                ors.onSuccess(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                    Log.e("matan", "data:" + new String(error.networkResponse.data));

                orf.onFailure(error);
           //}
        }
        });
        Log.d("matan","before adding request");
        rQueue.add(jsonRequest);
        Log.d("matan", "after adding request");
    }

}

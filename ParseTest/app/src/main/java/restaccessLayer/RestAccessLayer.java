package restaccessLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Alex on 1/9/2016.
 */
public class RestAccessLayer /*implements Response.Listener<Event[]>, Response.ErrorListener*/ {

    private static RestAccessLayer dataAccess;
    private String pathToConfFile;
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




    public void runGetRequest() {
        String url = "http://109.65.202.30:8081/gadera/restapi/events";
        Logger.getAnonymousLogger().info("**********start***************");
        // Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        System.out.println(response.substring(0,100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });

// Add the request to the queue
        rQueue.add(stringRequest);
    }

    public void runJsonRequestGetEvent(final RestCallback.OnResponseSuccess ors, final RestCallback.OnResponseFailure orf) throws IOException {
        String url = properties.get("ip") + "/"+properties.get("restApiPath") + "/" + properties.get("eventPath");

        Log.d("All_Gadera(runJsonRequestGetEvent)", url);
        final Request jsonRequest = new GsonRequest<Event[]> (Request.Method.GET,url, Event[].class, new Response.Listener<Event[]>(){
            @Override
            public void onResponse(Event[] response) {
                for(Event e : response) {
                    Log.i("All_Gadera", e.toString());
                }
                ors.onSuccess(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                orf.onFailure(error);
            }
        });

        rQueue.add(jsonRequest);
    }

//    @Override
//    public void onErrorResponse(VolleyError error) {
//
//    }
//
//
//    @Override
//    public void onResponse(Event[] response) {
//        for(Event e : response) {
//            Log.i("All_Gadera", e.toString());
//        }
//
//    }
}

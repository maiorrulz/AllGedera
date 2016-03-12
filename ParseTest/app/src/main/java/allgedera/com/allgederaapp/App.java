package allgedera.com.allgederaapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import entities.Business;


public class App extends Application {
    public static List<Business> genericEvents;
    public static List<String> cities;
    public  static Context g_context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Business.class);
        // matan: from where we got this strings?
        Parse.initialize(this, "1gOrgDgD5Wk615TD4vsZBrzI4z5m3El7Ua84cHeX", "qtUAWSNq0fGxXh4N3jljU1RroYeuGb0MQLzCn30U");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        g_context = getApplicationContext();

        if(g_context == null) Log.d(Constants._TAG, "OMG - g_context is null!");

    }

    private void init() {
        cities = new ArrayList<String>();
        genericEvents = new ArrayList<Business>();
    }
}

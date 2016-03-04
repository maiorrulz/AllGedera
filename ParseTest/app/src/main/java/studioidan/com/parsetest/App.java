package studioidan.com.parsetest;

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

import entities.GenericEvent;
import entities.Msg;
import entities.Place;
import entities.Work;

public class App extends Application {
    private static App _instance;
    public static List<Place> places;
    public static List<Work> works;
    public static List<GenericEvent> genericEvents;
    public static List<String> cities;
    public static List<Msg> msgs;

    final static String _TAG = "parsetest[App] : ";
    public  static Context g_context;
    /*public static App get_instance()
    {
        if(_instance==null)
            _instance=new App();
        return  _instance;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();

        init();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Place.class);
        ParseObject.registerSubclass(Work.class);
        ParseObject.registerSubclass(Msg.class);
        ParseObject.registerSubclass(GenericEvent.class);
        //Parse.initialize(this, "GqYD4PRVVZnVwMo0B2PWJazBY4frgVxU5EJt0Sft", "fNpOu5TeIhaNUcQs0cVGaurjMFQqoWC2hcZxmkJ6");  // me
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

        if(g_context == null) Log.d(_TAG, "OMG - g_context is null!");

    }

    private void init() {
        cities = new ArrayList<String>();
        places = new ArrayList<Place>();
        works = new ArrayList<Work>();
        msgs = new ArrayList<Msg>();
        genericEvents = new ArrayList<GenericEvent>();
    }
}

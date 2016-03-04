package entities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import com.android.volley.VolleyError;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import restaccessLayer.Event;
import restaccessLayer.RestAccessLayer;
import restaccessLayer.RestCallback;
import studioidan.com.parsetest.App;
import studioidan.com.parsetest.R;

/**
 * Created by elash on 15/12/2015.
 */
public class fakeEventsOrCoupons {

    //static List<GenericEvent> businesses = getFakeEvents();

    static int m_IsDataArrived = 0;
    static Event[] m_EventList = null;

    public static List<GenericEvent> getFakeEvents()
    {
        List<GenericEvent> toReturn = new ArrayList<GenericEvent>();
        final Resources res = Resources.getSystem();
        try {

            /**** Return "SERVER" events ****/
            RestAccessLayer rel = RestAccessLayer.getInstance(App.g_context, Environment.getExternalStorageDirectory() + "/config.properties");
            //RestCallback<Event[]> rc = new RestCallback<Event[]>();
            RestCallback.OnResponseSuccess success = new RestCallback.OnResponseSuccess<Event[]>() {
                @Override
                public void onSuccess(Event[] result) {
                    Log.i("All_Gadera", "Success Callback");
                    m_IsDataArrived = 1;
                    m_EventList = result;
                    //toReturn.add(_genEvt);
                    //}
                    // load events to generic, and put on "CouponActivity"
                }
            };

            RestCallback.OnResponseFailure failure = new RestCallback.OnResponseFailure() {
                @Override
                public void onFailure(Object result) {
                    m_IsDataArrived = -1; //if failed or server down or anything we don't know :) boom!
                    Log.i("All_Gadera", ((VolleyError)result).getMessage());
                }
            };

            rel.runJsonRequestGetEvent(success, failure);
        }
        catch (IOException e)
        {
            Log.i("All_Gadera(error)", e.getMessage());
            e.printStackTrace();
        }

        if(m_EventList != null) {
            for (int i = 0; i < m_EventList.length; i++) {
                Event _evt = m_EventList[i];
                GenericEvent _genEvt = new GenericEvent();
                _genEvt.setName(_evt.getName());
                _genEvt.setAbout(_evt.getAbout());
                _genEvt.setAddress(_evt.getAddress());
                _genEvt.setArea(_evt.getArea());
                _genEvt.setCategoty(_evt.getCategory());
                _genEvt.setImage(_evt.getId());
                _genEvt.setLocation(new ParseGeoPoint(_evt.getX_location(), _evt.getY_location()));
                //lets check quick
                Log.d("All_Gadera: ", _evt.getName());
                toReturn.add(_genEvt);
            }
        }


        return  toReturn;
    }


//    public static GenericEvent getFake(int i){
//        return businesses.get(i);
//    }

}

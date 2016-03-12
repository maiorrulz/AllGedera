package entities;

import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.android.volley.VolleyError;
import com.parse.ParseGeoPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import restaccessLayer.Event;
import restaccessLayer.RestAccessLayer;
import restaccessLayer.RestCallback;
import allgedera.com.allgederaapp.App;

/**
 * Created by elash on 15/12/2015.
 */
public class fakeEventsOrCoupons {

    static int m_IsDataArrived = 0;
    static Event[] m_BusinessesList = null;

    public static void loadBusinesses() {
        try {
            /**** Return "SERVER" events ****/
            RestAccessLayer rel = RestAccessLayer.getInstance(App.g_context);
            RestCallback.OnResponseSuccess success = new RestCallback.OnResponseSuccess<Event[]>() {
                @Override
                public void onSuccess(Event[] result) {
                    Log.i("matan", "Success Callback");
                    m_IsDataArrived = 1;
                    m_BusinessesList = result;
                }
            };
            RestCallback.OnResponseFailure failure = new RestCallback.OnResponseFailure() {
                @Override
                public void onFailure(Object result) {
                    m_IsDataArrived = -1; //if failed or server down or anything we don't know :) boom!
                    Log.i("matan", "RestCallback.OnResponseFailure failure " + result.toString()
                            +
                            ((VolleyError) result).getMessage());
                }
            };
            rel.runJsonRequestGetEvent(success, failure);
        } catch (IOException e) {
            Log.i("matan", e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<GenericEvent> getBusinesses() {
        List<GenericEvent> toReturn = new ArrayList<GenericEvent>();
        if(m_BusinessesList != null) {
            for (int i = 0; i < m_BusinessesList.length; i++) {
                Event _evt = m_BusinessesList[i];
                GenericEvent _genEvt = new GenericEvent();
                _genEvt.setName(_evt.getName());
                _genEvt.setAbout(_evt.getAbout());
                _genEvt.setAddress(_evt.getAddress());
                _genEvt.setArea(_evt.getArea());
                _genEvt.setCategoty(_evt.getCategory());
                //_genEvt.setImage(_evt.getId());
                _genEvt.setLocation(new ParseGeoPoint(_evt.getX_location(), _evt.getY_location()));
                toReturn.add(_genEvt);
            }
        }
        return  toReturn;
    }


    public static boolean existBusinesses() {
        return m_BusinessesList != null;
    }
}

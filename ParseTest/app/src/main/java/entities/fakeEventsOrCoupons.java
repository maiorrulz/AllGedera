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
            RestCallback.OnResponseSuccess success = new RestCallback.OnResponseSuccess<Event[]>() {
                @Override
                public void onSuccess(Event[] result) {
                    Log.i("All_Gadera", "Success Callback");
                    m_IsDataArrived = 1;
                    m_EventList = result;
                    // load events to generic, and put on "CouponActivity"
                }
            };

            RestCallback.OnResponseFailure failure = new RestCallback.OnResponseFailure() {
                @Override
                public void onFailure(Object result) {
                    m_IsDataArrived = -1; //if failed or server down or anything we don't know :) boom!
                    Log.i("All_gedera", "RestCallback.OnResponseFailure failure "+ result.toString()
                            +
                            ((VolleyError)result).getMessage());
                }
            };

            rel.runJsonRequestGetEvent(success, failure);
        }
        catch (IOException e)
        {
            Log.i("All_gedera(error)", e.getMessage());
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
                //_genEvt.setImage(_evt.getId());
                _genEvt.setLocation(new ParseGeoPoint(_evt.getX_location(), _evt.getY_location()));
                //lets check quick
                Log.d("All_Gadera: ", _evt.getName());
                toReturn.add(_genEvt);
            }
        }


        return  toReturn;
    }


}

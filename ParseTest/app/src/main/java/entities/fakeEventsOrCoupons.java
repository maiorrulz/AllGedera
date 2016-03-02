package entities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;

import restaccessLayer.Event;
import restaccessLayer.RestAccessLayer;

/**
 * Created by elash on 15/12/2015.
 */
public class fakeEventsOrCoupons {

    public static Context m_fakeEventContext;
    static List<GenericEvent> businesses = getFakeEvents();

    public static List<GenericEvent> getFakeEvents()
    {
        try
        {
            //put your config file path here plz
            RestAccessLayer.getInstance(m_fakeEventContext, "/sdcard/config.properties");
        }
        catch (Exception ex)
        {
            Log.d("[fakeEventsOrCoupons]::", ex.getMessage());
        }

        List<GenericEvent> toReturn = new ArrayList<GenericEvent>();

        List<Event> lstEvt=RestAccessLayer.GetFakeEventList();
        Log.w("inform","lstEvt.size(): "+lstEvt.size());

        for (Event _evt: lstEvt)
        {
            GenericEvent _genEvt = new GenericEvent();
            _genEvt.setName(_evt.getName());
            _genEvt.setAbout(_evt.getAbout());
            _genEvt.setAddress(_evt.getAddress());
            _genEvt.setArea(_evt.getArea());
            _genEvt.setCategoty(_evt.getCategory());
            _genEvt.setImage(_evt.getId());
            _genEvt.setLocation(new ParseGeoPoint(_evt.getX_location(), _evt.getY_location()));

            toReturn.add(_genEvt);
        }

        return  toReturn;
    }


    public static GenericEvent getFake(int i){
        return businesses.get(i);
    }

}

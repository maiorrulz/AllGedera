package entities;

import com.parse.ParseGeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elash on 15/12/2015.
 */
public class fakeEventsOrCoupons {

    public static List<GenericEvent> getFakeEvents(){

        /**** Return "SERVER" events ****/
        List<GenericEvent> toReturn=new ArrayList<GenericEvent>();
        toReturn.add(getFake(1));
        toReturn.add(getFake(2));
        return  toReturn;
    }

    public static GenericEvent getFake(int i){
        GenericEvent ge =new GenericEvent();
        ge.setName("gen name"+i);
        ge.setAbbout("gen about" + i);
        ge.setAddress("gen address" + i);
        ge.setPhone("054-5320007");
        ge.setLocation(new ParseGeoPoint(31.932111 + 0.002 * i, 34.801327 + 0.002 * i));
        //ge.setImage();
        return ge;
    }
}

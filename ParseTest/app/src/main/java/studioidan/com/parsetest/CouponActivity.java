package studioidan.com.parsetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import entities.GenericEvent;
import entities.fakeEventsOrCoupons;

public class CouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        // coupons names could not contain '~' symbol!
        fakeEventsOrCoupons.m_fakeEventContext = CouponActivity.this;
        List<GenericEvent> genericEvents= fakeEventsOrCoupons.getFakeEvents();
        int numOfCoupons=genericEvents.size();
        String[] coupons=new String[numOfCoupons];
        for(int i=0;i<numOfCoupons;i++){
            GenericEvent ge = fakeEventsOrCoupons.getFakeEvents().get(i);
            coupons[i]=ge.getName() +
                    "~"+ge.getAbout() +
                    "~"+ge.getImage();
        }

        ListAdapter listAdapter=
                new CustomAdapter(this,coupons);
       // Splash.writeToFile("here1");
        ListView couponsListView=(ListView) findViewById(R.id.couponsListView);
        couponsListView.setAdapter(listAdapter);
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

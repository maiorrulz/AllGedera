package studioidan.com.parsetest;

import android.app.Fragment;
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

        GenericEvent[] gea=null;

        List<GenericEvent> genericEvents= fakeEventsOrCoupons.getFakeEvents
                (this.getFragmentManager().getFragment(savedInstanceState,"")); // maybe th string "" shouldn't be empty

        // loop until gea is not null


        int numOfCoupons=genericEvents.size();
        String[] coupons=new String[numOfCoupons];
        for(int i=0;i<numOfCoupons;i++){
            GenericEvent ge = fakeEventsOrCoupons.getFakeEvents(this.getFragmentManager().getFragment(savedInstanceState,"")).get(i);
            coupons[i]=ge.getName() +
                    "~"+ge.getAbout() +
                    "~"+ge.getImage();
        }

        ListAdapter listAdapter=
               new CustomAdapter(this,coupons);
        Splash.writeToFile("here1");
        ListView couponsListView=(ListView) findViewById(R.id.couponsListView);
        couponsListView.setAdapter(listAdapter);
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

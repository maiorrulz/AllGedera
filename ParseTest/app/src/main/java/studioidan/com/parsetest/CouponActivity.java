package studioidan.com.parsetest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        new UpdateAsyncTask(this).execute();
    }

    public class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {

        Context m_cxt;
        ListAdapter listAdapter = null;
        ListView couponsListView = null;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            couponsListView = (ListView) findViewById(R.id.couponsListView);
        }

        public  UpdateAsyncTask(Context cxt)
        {
            m_cxt = cxt;
        }

        @Override
        protected Void doInBackground( Void... params)
        {
            Log.d("All_Gadera", "doInBackground - start");
            List<GenericEvent> genericEvents= fakeEventsOrCoupons.getFakeEvents();
            int numOfCoupons=genericEvents.size();

            String[] coupons=new String[numOfCoupons];
            for(int i=0;i<numOfCoupons;i++){
                GenericEvent ge = fakeEventsOrCoupons.getFakeEvents().get(i);
                coupons[i]=ge.getName() +
                        "~"+ge.getAbout() +
                        "~"+ge.getImage();
            }

            listAdapter = new CustomAdapter(m_cxt, coupons);
            Splash.writeToFile("here1");
            Log.d("All_Gadera", "doInBackground - end");
            return null;
        }

        @Override
        protected void onPostExecute( Void result ) {

            super.onPostExecute(result);
            couponsListView.setAdapter(listAdapter);

            Log.d("All_Gadera", "onPostExecute!");
            //finish();
        }
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

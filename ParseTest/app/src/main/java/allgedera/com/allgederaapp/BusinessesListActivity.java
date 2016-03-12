package allgedera.com.allgederaapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import entities.GenericEvent;
import entities.fakeEventsOrCoupons;

public class BusinessesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buisnesses_list);
        new UpdateAsyncTask(this).execute();

    }


    public class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {
        Context m_cxt;
        ListAdapter listAdapter = null;
        ListView couponsListView = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            couponsListView = (ListView) findViewById(R.id.couponsListView);
        }

        public  UpdateAsyncTask(Context cxt) {
            m_cxt = cxt;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            fakeEventsOrCoupons.loadBusinesses();
            waitUntilBusinessesLoaded();
            List<GenericEvent> genericEvents=fakeEventsOrCoupons.getBusinesses();
            int numOfCoupons=genericEvents.size();

            Log.d("matan","numOfCoupons="+numOfCoupons);

            String[] coupons=new String[numOfCoupons];
            for(int i=0;i<numOfCoupons;i++){
                GenericEvent ge = fakeEventsOrCoupons.getBusinesses().get(i);
                coupons[i]=ge.getName() +
                        "~"+ge.getAbout() +
                        "~"+ge.getImage();
            }
            listAdapter = new CustomAdapter(m_cxt, coupons);
            return null;
        }

        @Override
        protected void onPostExecute( Void result ) {
            super.onPostExecute(result);
            couponsListView.setAdapter(listAdapter);
        }
    }


    private void waitUntilBusinessesLoaded() {
        int k=0;
        while ((!fakeEventsOrCoupons.existBusinesses())&&k<20)
        try {
            k++;
            Thread.sleep(1000/k);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, BusinessesOnMapActivity.class);
        startActivity(intent);
    }
}

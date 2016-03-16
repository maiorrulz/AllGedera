package allgedera.com.allgederaapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import entities.Business;
import entities.BusinessManager;
import urlImages.ImageLoader;

public class BusinessesListActivity extends AppCompatActivity {

    private String strUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Everest_kalapatthar_crop.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buisnesses_list);
        new UpdateAsyncTask(this).execute();

    }

    public void onClick(View view) {
        Toast aa = Toast.makeText(this.getBaseContext(), "aa", Toast.LENGTH_LONG);
        aa.show();
    }

    public class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {
        Context m_cxt;
        ListAdapter listAdapter = null;
        ListView businessListView = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            businessListView = (ListView) findViewById(R.id.BUSINESS_LIST_VIEW);
        }

        public  UpdateAsyncTask(Context cxt) {
            m_cxt = cxt;
        }

        @Override
        protected Void doInBackground(Void... params) {
            BusinessManager.loadBusinesses();
            waitUntilBusinessesLoaded();
            List<Business> businessList= BusinessManager.getBusinesses();
            int numOfCoupons=businessList.size();
            String[] businesses=new String[numOfCoupons];
            for(int i=0;i<numOfCoupons;i++){
                Business business = BusinessManager.getBusinesses().get(i);
                businesses[i]=business.getName() +
                        "~"+business.getAbout() +
                        "~"+business.getImage();
            }
            listAdapter = new BusinessAdapter(m_cxt, businesses);
            return null;
        }

        @Override
        protected void onPostExecute( Void result ) {
            super.onPostExecute(result);
            businessListView.setAdapter(listAdapter);
        }
    }


    private void waitUntilBusinessesLoaded() {
        int k=0;
        while ((!BusinessManager.existBusinesses())&&k<20)
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

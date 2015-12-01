package studioidan.com.parsetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        // coupons names could not contain '~' symbol!
        String[] coupons={"coup1~22/1/2015","coup2~22/1/2015","coup3~22/1/2015"};
        ListAdapter listAdapter=
               new CustomAdapter(this,coupons);
        ListView couponsListView=(ListView) findViewById(R.id.couponsListView);
        couponsListView.setAdapter(listAdapter);
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

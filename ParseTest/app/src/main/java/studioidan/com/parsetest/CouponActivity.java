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
        String[] coupons={"coup1","coup2","coup3"};
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

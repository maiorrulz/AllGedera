package allgedera.com.allgederaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_menu);
        Profile profile = Profile.getCurrentProfile();

    }

    public void openCoupons(View view) {
        Intent intent = new Intent(this, CouponActivity.class);
        startActivity(intent);
    }
}

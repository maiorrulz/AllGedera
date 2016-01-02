package studioidan.com.parsetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;

import android.view.View;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            //((TextView) findViewById(R.id.welcomeText)).setText("Welcome " + profile.getName());
            Log.d("Profile:", "profile " + profile.getFirstName());
        }
        else {
            Log.d("profile:", "profile null");
        }
    }

    public void openCoupons(View view) {
        Intent intent = new Intent(this, CouponActivity.class);
        startActivity(intent);
    }
}

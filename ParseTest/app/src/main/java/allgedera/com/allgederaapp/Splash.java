package allgedera.com.allgederaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.GenericEvent;
//import entities.Place;
import entities.fakeEventsOrCoupons;


public class Splash extends Activity {
    /*public boolean isFinished;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        handler = new Handler();
        LoadData();
    }

    private void LoadData() {
        loadGenericEvents();
        isFinished = true;
    }



    public static void loadGenericEvents() {
        App.genericEvents =new ArrayList<GenericEvent>();
        for (int i = 0; i < fakeEventsOrCoupons.getFakeEvents().size(); i++)
            App.genericEvents.add(fakeEventsOrCoupons.getFakeEvents().get(i));

    }*/
}

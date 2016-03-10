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
    public boolean isFinished;
    int proccess = 0;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        handler = new Handler();
        LoadData();
      //  handler.postDelayed(runnable, 3000);
    }

    private void LoadData() {
        // getWorks();
        loadGenericEvents();
        getPlaces();
        //  getCities();
        //  getMsgs();

        isFinished = true;
    }

    private String tag = "Splash";
   /* Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (proccess < 3) {
                Log.i(tag, "notFinished");
                handler.postDelayed(this, 2000);
                return;
            }

            Splash.this.finish();


            //startActivity(new Intent(Splash.this, FirstTime.class))
            startActivity(new Intent(Splash.this, MainActivity.class));

            ParseObject.create("").saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                }
            });
        }
    };
*/
    public void getPlaces() {
        /*ParseQuery<Place> query = new ParseQuery<Place>("Place");
        query.findInBackground(new FindCallback<Place>() {
            @Override
            public void done(List<Place> list, ParseException e) {
                if (e == null) {
                    Log.i(tag, "got " + list.size() + " places!");
                    App.places = list;
                    proccess+=1;
                }
            }
        });*/
    }

    /*
    public void getMsgs() {
        ParseQuery<Msg> query = new ParseQuery<Msg>("Msg");
        query.findInBackground(new FindCallback<Msg>() {
            @Override
            public void done(List<Msg> list, ParseException e) {
                if (e == null) {
                    Log.i(tag, "got " + list.size() + " Msgs!");
                    App.msgs = list;
                    proccess+=1;
                }
            }
        });
    }
    public void getWorks() {

        //allWorkPlaces = new ArrayList<Work>();
        ParseQuery<Work> query = new ParseQuery<Work>("Work");
        query.findInBackground(new FindCallback<Work>() {
            @Override
            public void done(List<Work> list, ParseException e) {
                if (e == null) {
                    //for (Work w : list)
                    //allWorkPlaces.add(w);
                    Log.i(tag, "got " + list.size() + " Works!");
                   // writeToFile(list.get(1).toString());
                    App.works=list;

                    proccess+=1;
                }
            }

        });
    }
*/
    public static void loadGenericEvents() {
        App.genericEvents =new ArrayList<GenericEvent>();
        for (int i = 0; i < fakeEventsOrCoupons.getFakeEvents().size(); i++)
            App.genericEvents.add(fakeEventsOrCoupons.getFakeEvents().get(i));

    }


    static File gpxfile=null;
    static FileWriter gpxwriter=null;
    static BufferedWriter out=null;
    public static void writeToFile(String data) {
        try {
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()){
                gpxfile = new File(root, "matan_debug.txt");
                gpxwriter = new FileWriter(gpxfile);
                BufferedWriter out = new BufferedWriter(gpxwriter);
                out.write(data);
                out.close();
            }
        } catch (IOException e) {
            Log.e("Exception", "Could not write file " + e.getMessage());
        }
    }
    public static void writeToFile1(String data) {
        try {
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()){
                gpxfile = new File(root, "matan_debug1.txt");
                gpxwriter = new FileWriter(gpxfile);
                BufferedWriter out = new BufferedWriter(gpxwriter);
                out.write(data);
                out.close();
            }
        } catch (IOException e) {
            Log.e("Exception", "Could not write file " + e.getMessage());
        }
    }
    public static void writeToFile2(String data) {
        try {
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()){
                gpxfile = new File(root, "matan_debug2.txt");
                gpxwriter = new FileWriter(gpxfile);
                BufferedWriter out = new BufferedWriter(gpxwriter);
                out.write(data);
                out.close();
            }
        } catch (IOException e) {
            Log.e("Exception", "Could not write file " + e.getMessage());
        }
    }
    public static void writeToFile3(String data) {
        try {
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()){
                gpxfile = new File(root, "matan_debug3.txt");
                gpxwriter = new FileWriter(gpxfile);
                BufferedWriter out = new BufferedWriter(gpxwriter);
                out.write(data);
                out.close();
            }
        } catch (IOException e) {
            Log.e("Exception", "Could not write file " + e.getMessage());
        }
    }


    public static void closeOut() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    public void getCities() {
        JSONObject jsonObj = null;
        String xml = loadAssetTextAsString(this, "cities.xml");
        if (xml == null) {
            return;
        }
        try {
            List<String> cities = new ArrayList<String>();
            jsonObj = XML.toJSONObject(xml);
            JSONArray arr = jsonObj.getJSONObject("Root").getJSONArray("City");
            for (int i = 0; i < arr.length(); i++) {
                try {
                    String name = arr.getJSONObject(i).getString("Heb");
                    cities.add(name);
                } catch (Exception e) {
                    continue;
                }
            }
            App.cities.addAll(cities);
            cities.clear();
            proccess+=1;

        } catch (JSONException e) {
            Log.e("JSON exception", e.getMessage());
        }
    }

    private String loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e(tag, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(tag, "Error closing asset " + name);
                }
            }
        }

        return null;
    }
    */
}

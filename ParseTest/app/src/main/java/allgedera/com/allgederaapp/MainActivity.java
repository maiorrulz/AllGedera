package allgedera.com.allgederaapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import entities.MenuDrawerItem;
import fragments.Fragment_Main_Content;

import satellite.SatelliteMenu;
import satellite.SatelliteMenuItem;


public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener {

    AdapterMenuItem adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    Fragment_Main_Content fr_main_content;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceRTLIfSupported();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("כל גדרה");
        init();
        initArcMenu();
        createParseClassess();
        turnGpsOn();
    }

    private void turnGpsOn() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("שירותי מיקום לא פעילים...האם ברצונך להפעיל אותם?")
                    .setCancelable(false)
                    .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("לא תודה", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void createParseClassess() {
        ParseObject p = ParseObject.create("Place");
        p.put("category", "");
        p.put("name", "");
        p.put("address", "");
        p.put("location", new ParseGeoPoint());
        p.put("phone", "");
        p.put("minAge", "");
        p.put("area", "");
        p.put("prices", "");
        p.put("about", "");
        p.put("image", new ParseFile(new byte[]{}));
        p.saveInBackground();

        ParseObject w = ParseObject.create("Work");
        w.put("name", "");
        w.put("about", "");
        w.put("pay", "");
        w.put("category", "");
        w.put("address", "");
        w.put("phone", "");
        w.put("location", new ParseGeoPoint());
        w.put("area", "");
        w.put("image", new ParseFile(new byte[]{}));
        w.saveInBackground();
    }

    private void initArcMenu() {
        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(1, R.drawable.img_arc_work));
        menu.addItems(items);
        menu.setOnItemClickedListener(new SatelliteMenu.SateliteClickedListener() {
            public void eventOccured(int id) {
                if (id == 1) {
                    Toast.makeText(MainActivity.this, "עסקים", Toast.LENGTH_SHORT).show();
                    //fr_main_content.SetGenericEvents(App.genericEvents);
                    //fr_main_content.putBusinessesOnMap();
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void init() {
        fr_main_content = (Fragment_Main_Content) getSupportFragmentManager().findFragmentById(R.id.fragment);
        adapter = new AdapterMenuItem(MainActivity.this, new ArrayList<MenuDrawerItem>());
        adapter.data.add(new MenuDrawerItem("עסקים", R.drawable.img_menu_work));
        //drawerMenu
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item != null && item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
            return true;
        }

        //int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            fr_main_content.SetGenericEvents(App.genericEvents);

        mDrawerLayout.closeDrawers();
    }
}

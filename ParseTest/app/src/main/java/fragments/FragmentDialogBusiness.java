package fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import entities.GenericEvent;
import navigation.DownloadTask;
import studioidan.com.parsetest.R;
import studioidan.com.parsetest.Splash;

import static navigation.NavigationUtils.getDirectionsUrl;


public class FragmentDialogBusiness extends DialogFragment implements View.OnClickListener {

    GenericEvent ge;
    Button btnNav, btnShowWay, panView;
    ImageView imgExit;
//    Location myLocation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("matanMsg","onCreate");
        Splash.writeToFile1("FragmentDialogBusiness-> onCreate");
        super.onCreate(savedInstanceState);
        ge = (GenericEvent) getArguments().getSerializable("genericEvent");
        //myLocation = (Location) getArguments().getSerializable("myLocation");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   Splash.writeToFile3("FragmentDialogBusiness-> onCreateView return null!");

        //return null;
        View view = inflater.inflate(R.layout.fragment_dialog_business, container, false);

        imgExit = (ImageView) view.findViewById(R.id.img_exit);
        imgExit.setOnClickListener(this);

        btnNav = (Button) view.findViewById(R.id.btn_fragment_business_nav);
        btnNav.setOnClickListener(this);

        btnShowWay = (Button) view.findViewById(R.id.btn_fragment_business_show_way);
        btnShowWay.setOnClickListener(this);

        panView = (Button) view.findViewById(R.id.btn_fragment_business_pan_view);
        panView.setOnClickListener(this);

        TextView tvName = ((TextView) view.findViewById(R.id.tv_fragment_work_name));
        if (ge.getName() != null)
            tvName.setText(ge.getName());
        TextView tvAdress = ((TextView) view.findViewById(R.id.tv_fragment_work_about));
        if (ge.getAddress() != null)
            tvAdress.setText(ge.getAddress());
        TextView tvAbout = ((TextView) view.findViewById(R.id.tv_fragment_work_addess));
        if (ge.getAbout() != null)
            tvAbout.setText(ge.getAbout());
        TextView tvPhone = ((TextView) view.findViewById(R.id.tv_fragment_work_phone));
        if (ge.getPhone() != null)
            tvPhone.setText(ge.getPhone());


        ParseImageView imageView = (ParseImageView) view.findViewById(R.id.img_fragment_business_image);
        imageView.setImageResource(ge.getImage());

        /*final ParseImageView imageView = (ParseImageView) view.findViewById(R.id.img_fragment_business_image);
        ParseFile img = ge.getImage();
        if (img != null) {
            String url = img.getUrl();
            // UrlImageViewHelper.setUrlDrawable(imageView,url);
            imageView.setParseFile(img);
            imageView.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bmp);
                }
            });
        }
*/
        return view;
    }

    @Override
    public void onClick(View view) {
        Splash.writeToFile2(view.toString());
        if(view.getId() == R.id.img_exit)
        {
            dismiss();
        }
        else if (view.getId() == R.id.btn_fragment_business_nav) {
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" + "saddr="+ Fragment_Main_Content.myLocation.getLatitude() + "," + Fragment_Main_Content.myLocation.getLongitude() + "&daddr=" + ge.getLocation().getLatitude() + "," + ge.getLocation().getLongitude()));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
            dismiss();
        }
        else if (view.getId() == R.id.btn_fragment_business_show_way) {
            if (Fragment_Main_Content.currentPath != null)
                Fragment_Main_Content.currentPath.remove();
            LatLng source = new LatLng(Fragment_Main_Content.myLocation.getLatitude(), Fragment_Main_Content.myLocation.getLongitude());
            LatLng dest = new LatLng(ge.getLocation().getLatitude(), ge.getLocation().getLongitude());
            String url = getDirectionsUrl(source, dest);

            DownloadTask downloadTask = new DownloadTask();

            // Start downloading json data from Google Directions API
            downloadTask.execute(url);
            dismiss();
        }
        else if (view.getId() == R.id.btn_fragment_business_pan_view) {
            showStreetViewDialog(new LatLng(ge.getLocation().getLatitude(), ge.getLocation().getLongitude()));
        }
        else {
            try {
                String uri = "geo: " + ge.getLocation().getLatitude() + "," + ge.getLocation().getLongitude();
                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            } catch (Exception e) {
            }
        }

    }

    private void showStreetViewDialog(LatLng pos){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("streetview");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = StrretViewDialogFragment.newInstance(pos);
        newFragment.show(ft, "streetview");
    }


}

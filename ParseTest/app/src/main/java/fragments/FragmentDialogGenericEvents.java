package fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import entities.GenericEvent;
import entities.Place;
import entities.Work;
import studioidan.com.parsetest.R;
import studioidan.com.parsetest.Splash;


public class FragmentDialogGenericEvents extends DialogFragment implements View.OnClickListener {

    GenericEvent ge;
    Button btnNav;
    ImageView imgExit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Splash.writeToFile1("FragmentDialogGenericEvents-> onCreate");
        super.onCreate(savedInstanceState);
        ge = (GenericEvent) getArguments().getSerializable("genericEvent");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Splash.writeToFile3("FragmentDialogGenericEvents-> onCreateView return null!");
        return null;
        /*View v = inflater.inflate(R.layout.fragment_dialog_work, container, false);
        /*imgExit = (ImageView) v.findViewById(R.id.img_exit);
        imgExit.setOnClickListener(this);

        TextView tvName = ((TextView) v.findViewById(R.id.tv_fragment_work_name));
        if (ge.getName() != null)
            tvName.setText(ge.getName());
        TextView tvAdress = ((TextView) v.findViewById(R.id.tv_fragment_work_about));
        if (ge.getAddress() != null)
            tvAdress.setText(ge.getAddress());
        TextView tvAbout = ((TextView) v.findViewById(R.id.tv_fragment_work_addess));
        if (ge.getAbout() != null)
            tvAbout.setText(ge.getAbout());
        TextView tvPay = ((TextView) v.findViewById(R.id.tv_fragment_work_pay));
        if (ge.getPay() != null)
            tvPay.setText(ge.getPay());
        TextView tvPhone = ((TextView) v.findViewById(R.id.tv_fragment_work_phone));
        if (ge.getPhone() != null)
            tvPhone.setText(ge.getPhone());
        btnNav = (Button) v.findViewById(R.id.btn_fragment_work_nav);
        btnNav.setOnClickListener(this);

        final ParseImageView imageView = (ParseImageView) v.findViewById(R.id.img_fragment_work_image);
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
        return v;
        */
    }

    @Override
    public void onClick(View view) {
        Splash.writeToFile2(view.toString());
        if(view.getId()==R.id.img_exit)
        {
            dismiss();
        }
        else {
            try {
                String uri = "geo: " + ge.getLocation().getLatitude() + "," + ge.getLocation().getLongitude();
                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            } catch (Exception e) {
            }
        }

    }
}

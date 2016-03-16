package allgedera.com.allgederaapp;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import urlImages.ImageLoader;

/**
 * Created by elash on 17/11/2015.
 */
 class BusinessAdapter extends ArrayAdapter<String> {

    Context context;
    private ImageView imageView;
    private ImageLoader imgLoader;
    public BusinessAdapter(Context context, String[] businesses) {
        super(context, R.layout.custom_row,businesses);
        this.context=context;
    }

    @Override
    public View getView(int position,View convertView_notUsed,ViewGroup parent) {
        LayoutInflater couponsInflater = LayoutInflater.from(getContext());
        View customView = couponsInflater.inflate(R.layout.custom_row, parent, false);

        // the idea is to put "LargeText,date,LargeText..."
        String singleRow=getItem(position);

        String singleText = singleRow.substring(0, singleRow.indexOf("~"));

        singleRow = singleRow.substring(singleRow.indexOf("~")+1);

        String singleText_about = singleRow.substring(0, singleRow.indexOf("~"));

        singleRow.substring(singleRow.indexOf("~") + 1);

        String imageUrl = singleRow;
        // Business list element controls
        TextView couponText = (TextView) customView.findViewById(R.id.couponTitle);
       // Button btnLoadMoreDetails= (Button) customView.findViewById(R.id.button_moreDetails);
       // TextView couponText_date = (TextView) customView.findViewById(R.id.expireDate);

       /* ImageView couponImage = (ImageView) customView.findViewById(R.id.couponImage);
        Picasso.with(context)
                .load("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png")
                .resize(50, 50)
                .centerCrop()
                .into(couponImage);
        couponText.setText(singleText);
*/


        imageView = (ImageView) customView.findViewById(R.id.couponImage);
        imgLoader = new ImageLoader(context);
        imgLoader.displayImage(imageUrl, imageView);
//        couponText_date.setText(singleText_about);
       // couponImage.setImageResource(R.drawable.android_icon_256);


       //new ImageLoadTask("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png",
        //      couponImage).execute();
/*
        AQuery aq=new AQuery(this); // intsialze aquery
        aq.id(R.id.ImageView).image("http://www.vikispot.com/z/images/vikispot/android-w.png");
  */      return customView;
    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                Log.d("matan","doInBackground connection failed. "+e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            Log.d("matan", "is result = null?"+ (result==null));
            imageView.setImageBitmap(result);
        }

    }
}

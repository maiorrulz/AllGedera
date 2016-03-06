package allgedera.com.allgederaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by elash on 17/11/2015.
 */
 class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] coupons) {
        super(context, R.layout.custom_row,coupons);

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

        int im=Integer.parseInt(singleRow.substring(singleRow.indexOf("~")+1));

        TextView couponText = (TextView) customView.findViewById(R.id.couponTitle);

        TextView couponText_date = (TextView) customView.findViewById(R.id.expireDate);

        ImageView couponImage = (ImageView) customView.findViewById(R.id.couponImage);

        couponText.setText(singleText);

        couponText_date.setText(singleText_about);

        couponImage.setImageResource(im);

        return customView;
    }
}

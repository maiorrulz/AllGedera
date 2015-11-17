package studioidan.com.parsetest;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater couponsInflater = LayoutInflater.from(getContext());
        View customView = couponsInflater.inflate(R.layout.custom_row, parent, false);
        String singleText = getItem(position);
        TextView couponText = (TextView) customView.findViewById(R.id.couponTitle);
        ImageView couponImage = (ImageView) customView.findViewById(R.id.couponImage);
        couponText.setText(singleText);
        couponImage.setImageResource(R.drawable.conference2);
        return customView;
    }
}

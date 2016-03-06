package fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allgedera.com.allgederaapp.R;

/**
 * Created by PopApp_laptop on 20/05/2015.
 */
public class FragmentDialogContactUs extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);
        return v;
    }


}

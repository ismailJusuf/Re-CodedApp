package com.example.isma3el.re_codedapp;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hp on 21.02.2018.
 */

public class BaseFragment extends Fragment {


    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }

}

package com.LAW.Lift.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LAW.Lift.R;
import com.LAW.Lift.app.LiftApplication;


public class HowtouseFragment extends Fragment {



    public static HowtouseFragment newInstance() {
       return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.howtouse, container, false);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        LiftApplication.getInstance().trackScreenView("How to use");
    }

}


package com.LAW.Lift.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LAW.Lift.R;

/**
 * Created by santhoshis on 2/10/2016.
 */
public class DisclaimerFragment extends Fragment {



    public static DisclaimerFragment newInstance() {
        DisclaimerFragment fragmentStarter = new DisclaimerFragment();

        Bundle args = new Bundle();

        fragmentStarter.setArguments(args);
        return fragmentStarter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        android.app.ActionBar actionbar = getActivity().getActionBar();
        assert actionbar != null;
        actionbar.setTitle(getResources().getString(R.string.Disclaimer));

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.aimdisclaimer, container, false);
        return rootView;
    }


}

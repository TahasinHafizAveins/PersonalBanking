package com.iotsoftbd.personalbanking.ui.fragments.addMoney;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iotsoftbd.personalbanking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment {

    public AddMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }
}

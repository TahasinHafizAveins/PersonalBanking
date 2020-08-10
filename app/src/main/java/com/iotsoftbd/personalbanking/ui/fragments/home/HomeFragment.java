package com.iotsoftbd.personalbanking.ui.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.adapter.CustomAdapterGridView;
import com.iotsoftbd.personalbanking.ui.fragments.addMoney.AddMoneyFragment;
import com.iotsoftbd.personalbanking.ui.fragments.addNewAcc.AddNewAccFragment;
import com.iotsoftbd.personalbanking.ui.resetPS.ResetPassword;
import com.iotsoftbd.personalbanking.ui.userMain.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private GridView gridView;
    private CustomAdapterGridView adapterGridView;
    AddNewAccFragment addNewAccFragment = new AddNewAccFragment();
    AddMoneyFragment addMoneyFragment = new AddMoneyFragment();

    int[] categorys = {
            R.string.txt_new_account,
            R.string.txt_add_money,
            R.string.txt_view_details,
            R.string.txttransaction,
            R.string.txt_transfer_money,
            R.string.txt_reset
    };

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        android.view.View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView = view.findViewById(R.id.gridView);


        adapterGridView = new CustomAdapterGridView(HomeFragment.this, categorys);
        gridView.setAdapter(adapterGridView);

        action();
    }

    private void action() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    gridView.setEnabled(false);

                    if (position == 0) {
                        //New Account Request
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainContainer, addNewAccFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();

                    } else if (position == 1) {
                        //Add money
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainContainer, addMoneyFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (position == 2) {
                        //View Account Details
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainContainer, addNewAccFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();

                    } else if (position == 3) {
                        //View Transaction
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainContainer, addNewAccFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (position == 4) {
                        //Transfer Money
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainContainer, addNewAccFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (position == 5) {
                        //Reset Password
                        Intent intent = new Intent(getContext(), ResetPassword.class);
                        intent.putExtra("ACTIVITY", "main");
                        startActivity(intent);
                    } else gridView.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

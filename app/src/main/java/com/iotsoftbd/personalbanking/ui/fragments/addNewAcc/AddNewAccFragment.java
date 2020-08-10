package com.iotsoftbd.personalbanking.ui.fragments.addNewAcc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.iotsoftbd.personalbanking.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewAccFragment extends Fragment {

    private TextView tv_nid,tv_show_Acc_number,tv_show_current_balance;
    private Spinner sp_acc_type, sp_select_branch;
    private List<String> accTypeList = new ArrayList<>();
    private List<String> branchList = new ArrayList<>();

    public AddNewAccFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_add_new_acc, container, false);

        android.view.View view = inflater.inflate(R.layout.fragment_add_new_acc, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_nid = view.findViewById(R.id.tv_NID);
        tv_show_Acc_number = view.findViewById(R.id.tv_show_Acc_number);
        tv_show_current_balance = view.findViewById(R.id.tv_show_current_balance);
        sp_acc_type = view.findViewById(R.id.spnr_account_type_personal_info);
        sp_select_branch = view.findViewById(R.id.spnr_select_branch);

        accTypeList.add("Select");
        accTypeList.add("Savings");
        accTypeList.add("Current");

        branchList.add("Select");
        branchList.add("Dhaka");
        branchList.add("Savar");
        branchList.add("Dhanmondi");
        branchList.add("Banani");
        branchList.add("Narayanganj");

        ArrayAdapter<String> spAccTypeAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, accTypeList);
        spAccTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_acc_type.setAdapter(spAccTypeAdapter);

        ArrayAdapter<String> spBranchAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, branchList);
        spBranchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_select_branch.setAdapter(spBranchAdapter);

        action();

    }

    private void action() {

       /* sp_acc_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

    }
}

package com.iotsoftbd.personalbanking.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.ui.fragments.home.HomeFragment;


/**
 * Created by Aveins
 */

public class CustomAdapterGridView extends BaseAdapter {

    private HomeFragment homeFragment;
    //    private final String[] web;
    private final int[] nameID;
    private LayoutInflater inflater;

    public CustomAdapterGridView(HomeFragment homeFragment, int[] nameID) {
        this.homeFragment = homeFragment;
        this.inflater = LayoutInflater.from(homeFragment.getContext());
        this.nameID = nameID;
    }

    @Override
    public int getCount() {
        return nameID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid = null;
        if (convertView == null) {
            if (inflater != null) {
                grid = inflater.inflate(R.layout.grid_view, parent, false);
            }
        } else {
            grid = (View) convertView;
        }
        TextView text = grid.findViewById(R.id.grid_text);
        text.setText(nameID[position]);
        text.setGravity(Gravity.CENTER);


        return grid;

    }
}

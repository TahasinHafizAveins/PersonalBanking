package com.iotsoftbd.personalbanking;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private LinearLayout llDrawerHome,llDrawerNewAcc,llDrawerAccDetails,
            llDrawerTransactions,llDrawerCashOut,llDrawerTransfer,llDrawerAddMoney,
            llDrawerResetPassword;
    private TextView tvDrawerUserName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        //setUp Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_sliding_menu);
        toggle.syncState();

        //toolbar Call
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    drawer.openDrawer(GravityCompat.START);
                    toolbar.setFocusableInTouchMode(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        llDrawerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    drawer.closeDrawer(GravityCompat.START);
                    toolbar.setFocusableInTouchMode(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar_home_activity);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        llDrawerHome = navigationView.findViewById(R.id.ll_drawer_home);
        llDrawerNewAcc = navigationView.findViewById(R.id.ll_drawer_new_account);
        llDrawerAccDetails = navigationView.findViewById(R.id.ll_drawer_view_account_details);
        llDrawerTransactions = navigationView.findViewById(R.id.ll_drawer_view_transaction);
        llDrawerAddMoney = navigationView.findViewById(R.id.ll_drawer_add_money);
        llDrawerCashOut = navigationView.findViewById(R.id.ll_drawer_cash_out);
        llDrawerTransfer = navigationView.findViewById(R.id.ll_drawer_transfer_money);
        llDrawerResetPassword = navigationView.findViewById(R.id.ll_drawer_reset_password);
    }
}

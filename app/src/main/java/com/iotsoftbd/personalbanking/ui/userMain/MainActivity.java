package com.iotsoftbd.personalbanking.ui.userMain;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.ui.fragments.addNewAcc.AddNewAccFragment;
import com.iotsoftbd.personalbanking.ui.fragments.home.HomeFragment;
import com.iotsoftbd.personalbanking.ui.resetPS.ResetPassword;
import com.iotsoftbd.personalbanking.ui.userAuth.userLogin.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContact.View {

    private MainPresenter mPresenter;
    private String uid, username;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private LinearLayout llDrawerHome,llDrawerNewAcc,llDrawerAccDetails,
            llDrawerTransactions,llDrawerCashOut,llDrawerTransfer,llDrawerAddMoney,
            llDrawerResetPassword,llDrawerLogout;
    private TextView tvDrawerUserName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        init();


        // default Activity
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new HomeFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.checkCurrentUSer();
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
        llDrawerLogout = navigationView.findViewById(R.id.ll_drawer_Logout);
        tvDrawerUserName = navigationView.findViewById(R.id.tv_username_drawer);

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

        //action
        action();
    }

    private void action() {
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


        //Nav--> home
        llDrawerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new HomeFragment()).commit();
                    toolbar.setFocusableInTouchMode(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //Nav--> addAccount
        llDrawerNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new AddNewAccFragment()).commit();
                    toolbar.setFocusableInTouchMode(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        llDrawerResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetPassword.class);
                intent.putExtra("ACTIVITY", "main");
                startActivity(intent);
            }
        });

        //Nav--> logout
        llDrawerLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                mPresenter.logout();
            }
        });
    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void loadHomeActivity(String uid) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new HomeFragment()).commit();
        this.uid = uid;
        tvDrawerUserName.setText(username);
    }

    @Override
    public void loadToolberName(String userName) {

        this.username = userName;

    }

    @Override
    public void reCreate() {
        recreate();
    }
}

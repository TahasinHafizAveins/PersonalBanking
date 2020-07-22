package com.iotsoftbd.personalbanking.accounceSection.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.accounceSection.mainBalance.MainBalenceActivity;

public class AccounceHomeActivity extends AppCompatActivity {

    Button btn_mainBalance,btn_addBalance,btn_cashout,btn_shareBalance,btn_tbalancehistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounce_home);

        btn_mainBalance = findViewById(R.id.main_balance);
        btn_cashout = findViewById(R.id.cash_out);
        btn_shareBalance = findViewById(R.id.share_balance);
        btn_tbalancehistory = findViewById(R.id.transaction_history);
        btn_addBalance = findViewById(R.id.add_balance);

        btn_mainBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccounceHomeActivity.this, MainBalenceActivity.class));
            }
        });
    }
}

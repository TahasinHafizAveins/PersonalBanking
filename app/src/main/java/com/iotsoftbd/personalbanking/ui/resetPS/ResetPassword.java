package com.iotsoftbd.personalbanking.ui.resetPS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.ui.userAuth.userLogin.LoginActivity;
import com.iotsoftbd.personalbanking.ui.userMain.MainActivity;

public class ResetPassword extends AppCompatActivity {

    EditText email;
    Button resetPassword;
    Button cancel;
    String activity;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        this.activity = getIntent().getStringExtra("ACTIVITY");

        Log.d("Dddd","ddd "+activity);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.reset_email);
        resetPassword = findViewById(R.id.reSend);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.equals("login")){
                    finish();
                    Intent intent = new Intent(ResetPassword.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    finish();
                    Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();

                if (userEmail.isEmpty()) {
                    Toast.makeText(ResetPassword.this, "Please Enter Registered Email ID", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPassword.this, "Password Reset Send", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                            } else {
                                Toast.makeText(ResetPassword.this, "Error send", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}

package com.iotsoftbd.personalbanking.ui.userAuth.userLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iotsoftbd.personalbanking.ui.userMain.MainActivity;
import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.models.User;
import com.iotsoftbd.personalbanking.ui.userAuth.userReg.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private TextInputEditText email,password;
    Button forget_ps;
    Button btn_login;
    Button btn_reg;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inits();
    }

    private void inits() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forget_ps = findViewById(R.id.link_reset);
        btn_login = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.link_signUp);

        mPresenter = new LoginPresenter(this);

        action();


    }

    private void action() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(email.getText().toString(),password.getText().toString());
                Boolean valid = mPresenter.validateUser(user);
                if (!valid) {
                    return;
                }
                mPresenter.signIn(user);
            }
        });
    }

    @Override
    public void showErrorToast(int fieldnumber, String message) {

    }

    @Override
    public void startActivity(User user) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void resetPassword() {

    }

    @Override
    public void regActivity() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}

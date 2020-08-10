package com.iotsoftbd.personalbanking.ui.userAuth.userReg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iotsoftbd.personalbanking.R;
import com.iotsoftbd.personalbanking.models.User;
import com.iotsoftbd.personalbanking.ui.userAuth.userLogin.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity implements RegContract.View{

    private TextInputEditText email,password,u_profession,u_dob,u_phn,u_nid;
    private Button btn_reg;
    private Button btn_login;

    private RegPresenter mPresenter;

    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        inits();
    }

    private void inits() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        u_profession = findViewById(R.id.et_uprofession);
        u_dob = findViewById(R.id.et_uDob);
        u_phn = findViewById(R.id.et_phn_no);
        u_nid = findViewById(R.id.et_nid);

        //button
        btn_login = findViewById(R.id.login);
        btn_reg = findViewById(R.id.btn_Registration);

        u_dob.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        u_phn.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        u_nid.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        //init presenter
        mPresenter = new RegPresenter(this);



        actions();

    }

    private void actions() {

       final Calendar myCalendar = Calendar.getInstance(Locale.ENGLISH);

       final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                u_dob.setEnabled(true);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                RegistrationActivity.this.updateLabel(myCalendar);
            }
        };

        u_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_dob.setEnabled(false);
                long rt = System.currentTimeMillis() - (6576 * 24 * 60 * 60 * 1000L);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(rt);
                datePickerDialog.setCanceledOnTouchOutside(false);
                datePickerDialog.show();
                datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        u_dob.setEnabled(true);
                        if (u_dob.getError() != null) {
                            u_dob.setError(null);
                        }
                    }
                });
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(email.getText().toString(),password.getText().toString());
                Boolean valid = mPresenter.validate(user);
                if (!valid) {
                    return;
                }
                mPresenter.signUp(user);
            }
        });
    }

    @Override
    public void showErrorToast(int fieldnum, String message) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createUserdb(String uid) {
        //String email, String password, String profession, String userDOB, String userNid, String phn

        user = new User(email.getText().toString(),password.getText().toString(),u_profession.getText().toString(),
                u_dob.getText().toString(),u_nid.getText().toString(),u_phn.getText().toString());
        mPresenter.createUserdb(user,uid);

    }


    @Override
    public void startActivity() {

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    @Override
    public void thisActivity() {

    }

    private void updateLabel(Calendar myCalendar) {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        u_dob.setText(sdf.format(myCalendar.getTime()));
    }
}

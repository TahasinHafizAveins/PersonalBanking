package com.iotsoftbd.personalbanking.userAuth.userogin;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.iotsoftbd.personalbanking.models.User;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public Boolean validateUser(User user) {
        if (user.getEmail().isEmpty()){
            mView.showErrorToast(6,"Enter valid E-mail");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()){
            mView.showErrorToast(6,"Enter valid E-mail");
            return false;
        }
        if (user.getPassword().length()<6){
            mView.showErrorToast(7,"Password length must be more then 6 character");
            return false;
        }
        return true;
    }

    @Override
    public void signIn(final User user) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   mView.startActivity(user);
                   mView.showToast("Login Successfull");
               }else {
                   mView.showToast("Login faild");
               }
            }
        });

    }
}

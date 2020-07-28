package com.iotsoftbd.personalbanking.userAuth.userReg;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iotsoftbd.personalbanking.models.User;

import java.util.regex.Pattern;

public class RegPresenter implements RegContract.Presenter {

    private RegContract.View mView;

    private DatabaseReference mDatabaseRef;

    String uid = "";

    public RegPresenter(RegContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(User user) {

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
    public void signUp(User user) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    if (firebaseUser != null){
                        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    uid = firebaseUser.getUid();
                                    mView.showToast("sucessfull sent verification code");
                                    mView.createUserdb(uid);
                                    FirebaseAuth.getInstance().signOut();
                                    mView.showToast(uid);
                                }
                                else {
                                    mView.showToast("Unable to send verification code");
                                }
                            }
                        });

                    }else {
                        mView.showToast("Couldn't find User");
                    }
                }else {
                    mView.showToast("Unable Registering User");
                }
            }
        });
    }

    @Override
    public void createUserdb(User user, String uid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(uid).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                mView.showToast("successfully created");
                mView.startActivity();
            }
        });
    }

}

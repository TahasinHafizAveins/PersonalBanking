package com.iotsoftbd.personalbanking.ui.userMain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPresenter implements MainContact.Presenter {

    private MainContact.View mView;
    private FirebaseUser mCurrentUser;

    public MainPresenter(MainContact.View mView) {
        this.mView = mView;
        this.mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void checkCurrentUSer() {
        if (mCurrentUser == null){
            mView.startLoginActivity();
        }else {
            String userName = userNameFromEmail(mCurrentUser.getEmail());
            mView.loadToolberName(userName);
            mView.loadHomeActivity(mCurrentUser.getUid());
        }
    }

    @Override
    public void loadHomeActivity() {

        String userName = userNameFromEmail(mCurrentUser.getEmail());
        mView.loadToolberName(userName);
        mView.loadHomeActivity(mCurrentUser.getUid());

    }

    private String userNameFromEmail(String email) {
        if (email.contains("@")){
            return email.split("@")[0];
        }else return email;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        mView.reCreate();
    }
}

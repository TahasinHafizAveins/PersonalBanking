package com.iotsoftbd.personalbanking.ui.userAuth.userLogin;

import com.iotsoftbd.personalbanking.models.User;

public interface LoginContract {
    interface Presenter{
        Boolean validateUser(User user);

        void signIn(User user);
    }
    interface View{

        void showErrorToast(int fieldnumber,String message);
        void startActivity(User user);
        void resetPassword();
        void regActivity();
        void showToast(String message);
    }
}

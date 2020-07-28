package com.iotsoftbd.personalbanking.userAuth.userReg;

import com.iotsoftbd.personalbanking.models.User;

public interface RegContract {
    interface Presenter{
        boolean validate(User user);
        void signUp(User user);
        void createUserdb(User user, String uid);
    }
    interface View{

        void showErrorToast(int fieldnum,String message);
        void showToast(String message);
        void createUserdb(String uid);
        void startActivity();
        void thisActivity();
    }
}

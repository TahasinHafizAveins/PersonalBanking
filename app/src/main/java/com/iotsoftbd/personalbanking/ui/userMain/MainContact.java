package com.iotsoftbd.personalbanking.ui.userMain;

public interface MainContact {
    public interface Presenter{

        void checkCurrentUSer();
        void loadHomeActivity();
        void logout();
    }

    public interface View{

        void startLoginActivity();
        void loadHomeActivity(String uid);
        void loadToolberName(String userName);
        void reCreate();
    }
}

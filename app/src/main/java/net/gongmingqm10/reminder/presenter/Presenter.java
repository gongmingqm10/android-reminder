package net.gongmingqm10.reminder.presenter;

import net.gongmingqm10.reminder.ui.activity.view.BaseView;

public interface Presenter {
    void onStart();
    void onStop();
    void attachView(BaseView view);
}

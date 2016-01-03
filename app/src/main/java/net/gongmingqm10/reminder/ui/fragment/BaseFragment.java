package net.gongmingqm10.reminder.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

import net.gongmingqm10.reminder.ui.activity.BaseActivity;

public class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).inject(this);
        }
    }
}

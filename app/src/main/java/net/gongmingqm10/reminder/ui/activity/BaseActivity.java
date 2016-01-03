package net.gongmingqm10.reminder.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.gongmingqm10.reminder.ReminderApp;
import net.gongmingqm10.reminder.data.PreferenceMgr;
import net.gongmingqm10.reminder.presenter.Presenter;
import net.gongmingqm10.reminder.ui.activity.modules.ActivityModule;
import net.gongmingqm10.reminder.ui.activity.view.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog loadingDialog;
    private ObjectGraph activityGraph;

    @Inject
    PreferenceMgr preferenceMgr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initInject();

        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    private void initInject() {
        ReminderApp application = (ReminderApp) getApplication();
        activityGraph = application.getApplicationGraph().plus(getCombinedModules().toArray());
        inject(this);
    }

    /**
     * A list of modules to use for the individual activity graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected List<Object> getModules() {
        return new ArrayList<>();
    }

    private List<Object> getCombinedModules() {
        List<Object> modules = new ArrayList<>(getModules());
        modules.add(new ActivityModule(this));
        return modules;
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        activityGraph.inject(object);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loading(String message) {
        loaded();
        loadingDialog = ProgressDialog.show(this, null, message, false);
    }

    @Override
    public void loaded() {
        if (loadingDialog != null && !isFinishing() && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public abstract Presenter getPresenter();
}

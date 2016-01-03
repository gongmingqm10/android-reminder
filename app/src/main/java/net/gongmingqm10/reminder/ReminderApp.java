package net.gongmingqm10.reminder;

import android.app.Application;

import net.gongmingqm10.reminder.ui.activity.modules.AndroidModule;

import dagger.ObjectGraph;

public class ReminderApp extends Application {
    private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationGraph = ObjectGraph.create(new AndroidModule(this));
        inject(this);
    }

    public void inject(Object object) {
        applicationGraph.inject(object);
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}

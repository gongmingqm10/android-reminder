package net.gongmingqm10.reminder.ui.activity.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import net.gongmingqm10.reminder.data.PreferenceMgr;
import net.gongmingqm10.reminder.ui.activity.BaseActivity;
import net.gongmingqm10.reminder.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                // Here inject simple classes that no need a presenter
                MainActivity.class
        },
        addsTo = AndroidModule.class,
        library = true)
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    /**
     * Allow the activity context to be injected but require that it be annotated with
     * {@link InjectActivity @InjectActivity} to explicitly differentiate it from application context.
     */
    @Provides
    @Singleton
    @InjectActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(@InjectApplication Application app) {
        return app.getSharedPreferences("reminder_prefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferenceMgr providePreferenceMgr(SharedPreferences prefs) {
        return new PreferenceMgr(prefs);
    }
}

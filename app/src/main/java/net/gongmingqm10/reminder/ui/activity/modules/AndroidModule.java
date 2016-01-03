package net.gongmingqm10.reminder.ui.activity.modules;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import net.gongmingqm10.reminder.ReminderApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {ReminderApp.class},
        library = true)
public class AndroidModule {
    private final ReminderApp application;

    public AndroidModule(ReminderApp application) {
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link InjectApplication @InjectApplication} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @InjectApplication
    Application provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }

}

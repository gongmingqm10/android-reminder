package net.gongmingqm10.reminder.data;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferenceMgr {
    private SharedPreferences sharedPreferences;

    public PreferenceMgr(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public void remove(String key) {
        getEditor().remove(key).apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        getEditor().putInt(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void putLong(String key, long value) {
        getEditor().putLong(key, value).apply();
    }
}

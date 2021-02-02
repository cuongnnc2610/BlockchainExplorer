package com.example.blockchainexplorer.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.example.blockchainexplorer.utils.Constants;

import java.io.File;
import java.io.IOException;

public class BlockchainExplorerApplication extends Application implements LifecycleObserver {

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        File oldFile = new File(getCacheDir(), sharedPreferences.getString(Constants.BLOCKS_CACHE_FILE_NAME, ""));
        if (!oldFile.isFile()) {
            try {
                File file = File.createTempFile(Constants.BLOCKS_CACHE_FILE_NAME, null, getCacheDir());
                editor.putString(Constants.BLOCKS_CACHE_FILE_NAME, file.getName());
                editor.apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

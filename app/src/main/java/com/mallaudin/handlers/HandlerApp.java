package com.mallaudin.handlers;

import android.app.Application;

/**
 * Created on 2016-12-01 21:49.
 *
 * @author M.Allaudin
 */
public class HandlerApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    } // onCreate

} // HandlerApp

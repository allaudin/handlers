package com.mallaudin.handlers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mallaudin.handlers.ActivityUtils;
import com.mallaudin.handlers.R;
import com.mallaudin.handlers.Utils;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final boolean LOG = true;
    private WorkerThread workerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils.setToolbar(this, getString(R.string.app_name));
    } // onCreate

    @Override
    protected void onStart() {
        super.onStart();
        if(LOG){
            log("onStart()");
        }

        workerThread = new WorkerThread();
        workerThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(LOG){
            log("onStop()");
        }
        if(workerThread != null){
            if(LOG){
                log("onStop -> %s", "quit looper.");
            }
            
            workerThread.getHandler().getLooper().quit();
            workerThread = null;
        }
    } // onStop

    private void log(String format, Object... args){
        Utils.log("class[MainActivity]", String.format(format, args));
    } // log

    public void onDoWork(View view) {
        if(workerThread.getHandler() != null){
            int what = new Random().nextInt(100);
            if(LOG){
                log("onDoWork -> seding [%d]", what);
            }

            workerThread.getHandler().sendMessage(workerThread.getHandler().obtainMessage(what));
        }
    } // onDoWork

} // MainActivity

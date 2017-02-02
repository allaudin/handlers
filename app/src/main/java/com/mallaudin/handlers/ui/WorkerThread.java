package com.mallaudin.handlers.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.mallaudin.handlers.Utils;

import java.util.Random;

/**
 * Created on 2017-02-02 11:28.
 *
 * @author M.Allaudin
 */
class WorkerThread extends Thread implements Handler.Callback{

    private static final boolean LOG = true;

    private Handler mHandler;

    WorkerThread() {
        super("WorkerThread");
    } // WorkerThread

    @Override
    public void run() {
        Looper.prepare(); // attached looper to current thread
        mHandler = new Handler(this); // attach this handler to looper of current thread
        Looper.loop(); // starts to process queue, this is a blocking call.

        if(LOG){
            log("run -> %s", "terminating worker thread.");
        }

    } // run


    @Override
    public boolean handleMessage(Message msg) {
        try {
            int sleepTime = new Random().nextInt(2000);
            if(LOG){
                log("handleMessage -> sleeping for [%s ms]", sleepTime);
            }
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(LOG){
            log("handleMessage -> what[%s], thread[%s]", msg.what, Thread.currentThread().getName());
        }
        return true;
    } // handleMessage

    Handler getHandler(){
        return mHandler;
    } // getHandler

    private void log(String format, Object... args){
        Utils.log("class[WorkerThread]", String.format(format, args));
    } // log

} // WorkerThread

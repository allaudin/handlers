package com.mallaudin.handlers;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;

/**
 * Created on 2016-12-01 17:19.
 *
 * @author M.Allaudin
 */
@SuppressWarnings({"unused, StaticFieldLeak, HardwareIds", "WeakerAccess"})
public class Utils {

    private static final String TAG = HandlerApp.class.getSimpleName().toLowerCase();
    private static final String APP_PREFS = HandlerApp.class.getSimpleName().toLowerCase() + "_prefs";

    private static Context appContext;

    private Utils() {
        throw new AssertionError("Instance is against our will.");
    } // Utils

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    } // init

    public static Context getAppContext(){
        checkContextInitialized();
        return appContext;
    } // getAppContext

    public static String getUuid() {
        checkContextInitialized();
        return Settings.Secure.getString(appContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    } // getUuid

    public static float pxToDp(float px){
        return px / Resources.getSystem().getDisplayMetrics().density;
    } // pxToDp

    public static float dpToPx(float dp){
        return dp * Resources.getSystem().getDisplayMetrics().density;
    } // dpToPx

	public static void addToPrefs(String key, String value){
		getAppContext()
				.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
				.edit().putString(key, value)
				.apply();
	} // addToPrefs

	public static String getFromPrefs(String key, String def){
		return getAppContext()
				.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
				.getString(key, def);
	} // addToPrefs

    public static void log(String tag, String message) {
        try{
            Log.d(TAG, String.format("%s -> %s", tag, message));
        }catch (Exception e){
            e.printStackTrace();
        }
    } // log

    private static void checkContextInitialized(){
      if(appContext == null)
        throw new IllegalStateException(String.format("appContext in %s is not initialized. Call Utils.init to initialize it.", Utils.class.getName()));
    } // checkContextInitialized
    
} // Utils

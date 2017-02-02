package com.mallaudin.handlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created on 2016-12-01 22:11.
 *
 * @author M.Allaudin
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ActivityUtils {

    private ActivityUtils() {
        throw new AssertionError("No instance allowed.");
    } // ActivityUtils

    public static void setToolbar(AppCompatActivity activity, @NonNull String title) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        final ActionBar actionbar = activity.getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(title);
        }
    } // setToolbar

    public static void hideSoftKeyboard(AppCompatActivity activity){
        View currentFocus = activity.getCurrentFocus();
        if(currentFocus != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    } // hideKeyboard

    public static void showSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) Utils.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    } // showKeyboard

    public static ProgressDialog getProgressDialog(Context uiContext, @StringRes int titleResource, @StringRes int messageResource){
        ProgressDialog progressDialog = new ProgressDialog(uiContext);
        progressDialog.setTitle(uiContext.getString(titleResource));
        if(messageResource != -1) {
            progressDialog.setMessage(uiContext.getString(messageResource));
        }
        return progressDialog;
    } // getProgressDialog

    public static ProgressDialog getProgressDialog(Context uiContext, @StringRes int titleResource){
        return getProgressDialog(uiContext, titleResource, -1);
    } // getProgressDialog

} // ActivityUtils

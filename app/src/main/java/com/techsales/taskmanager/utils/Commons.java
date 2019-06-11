package com.techsales.taskmanager.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.techsales.taskmanager.R;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class Commons {

    public static void dispose(Disposable... disposables) {
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                if (disposable != null)
                    disposable.dispose();
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && (networkInfo.isConnected());
        }
        return false;
    }

    public static ProgressDialog showLoadingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        return showLoadingDialog(context, context.getResources().getString(R.string.message_loading));
    }

    public static void showSnackBar(Context context, final View layout, String message) {
        Snackbar snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        View view = snackbar.getView();
        view.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
        TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.error_snackbar_src, 0, 0, 0);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.spacing_small));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        snackbar.show();
    }


    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

}

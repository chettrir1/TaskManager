package com.techsales.taskmanager.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.techsales.taskmanager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
        return showLoadingDialog(context, context.getResources().getString(R.string.progress_dialog_loading));
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
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        snackbar.show();
    }

    public static boolean isEmpty(List list) {
        return list != null && !list.isEmpty();
    }

    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    public static Date getCurrentDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static String getFormattedDateString(Date date) {
        String finalDate = "";
        try {
            SimpleDateFormat spf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.getDefault());
            String dateString = spf.format(date);

            Date newDate = spf.parse(dateString);
            spf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault());
            if (newDate != null) {
                finalDate = spf.format(newDate);
            }
            return finalDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openKeyboard(final Context context) {
        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 500);
    }

    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getParsedDay(String date) {
        String parsed_date = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+5:45"));
            Date value = formatter.parse(date);

            if (value != null) {
                String[] splited = value.toString().split("\\s+");
                parsed_date = String.valueOf(splited[0]);
            }
        } catch (Exception ignored) {
        }
        return parsed_date;
    }

}

package com.techsales.taskmanager.auth.login;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.login.UserInfo;
import com.techsales.taskmanager.data.model.viewmodel.login.LoginViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Disposable disposable;
    private TaskManagerComponent component;
    private static final String TAG = LoginPresenter.class.getSimpleName();

    LoginPresenter(TaskManagerComponent component, LoginContract.View view) {
        this.view = view;
        this.component = component;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void onLogin(String username, String password, String token) {
        view.showLoginProgress();
        disposable = component.data().requestLogin(username, password, token)
                .subscribe(userInfo -> {
                    LoginViewModel loginViewModel = UserInfo.mapToViewModel(userInfo);
                    view.showLoginSuccess(loginViewModel);
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showLoginError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNetworkNotAvailableError();
                    else
                        view.showLoginError(component.context().getString(R.string.error_server));
                });

    }

    @Override
    public void getFirebaseToken(Activity activity) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity,
                instanceIdResult -> {
                    String token = instanceIdResult.getToken();
                    Log.v("token", token);
                    view.setFirebaseToken(token);
                });
    }
}

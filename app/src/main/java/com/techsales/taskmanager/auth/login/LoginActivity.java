package com.techsales.taskmanager.auth.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.login.LoginViewModel;
import com.techsales.taskmanager.databinding.ActivityLoginBinding;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.utils.Commons;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    private ProgressDialog progressDialog;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setRememberStatus();
        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            if (TextUtils.isEmpty(username)) {
                showEmptyUsername(getResources().getString(R.string.empty_field_message));
            } else if (TextUtils.isEmpty(password)) {
                showEmptyPassword(getResources().getString(R.string.empty_field_message));
            } else {
                if (binding.cbRememberMe.isChecked()) {
                    data.rememberChecked(username, password, true);
                } else {
                    data.rememberUnchecked();
                }
                presenter.onLogin(username, password, "sadasd");
            }
        });


    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();

    }

    @Override
    public void showEmptyUsername(String message) {
        binding.etUsernameLayout.setError(message);
    }

    @Override
    public void showEmptyPassword(String message) {
        binding.etPasswordLayout.setError(message);
    }

    @Override
    public void showLoginProgress() {
        progressDialog = Commons.showLoadingDialog(this, getResources().getString(R.string.veryfing_login));
    }

    @Override
    public void showLoginSuccess(LoginViewModel loginViewModel) {
        dismissProgress();
        DashboardActivity.start(LoginActivity.this, loginViewModel.getUserId());
        finish();
    }

    @Override
    public void showLoginError(String message) {
        dismissProgress();
        Commons.showSnackBar(this, binding.rlMain, message);
    }

    @Override
    public void showNetworkNotAvailableError() {
        dismissProgress();
        Commons.showSnackBar(this, binding.rlMain, getString(R.string.network_not_available_error));
    }

    private void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void setRememberStatus() {
        if (data.savedUsername() != null && data.savedPassword() != null && data.savedRememberStatus()) {
            binding.etUsername.setText(data.savedUsername());
            binding.etPassword.setText(data.savedPassword());
            binding.cbRememberMe.setChecked(true);
        }
    }


}

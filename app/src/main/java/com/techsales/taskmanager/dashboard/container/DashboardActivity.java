package com.techsales.taskmanager.dashboard.container;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.contacts.container.ContactsActivity;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.databinding.ActivityDashboardBinding;
import com.techsales.taskmanager.notes.container.NoteListActivity;
import com.techsales.taskmanager.notification.container.NotificationActivity;
import com.techsales.taskmanager.profile.container.ProfileActivity;
import com.techsales.taskmanager.utils.Commons;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity implements DashboardContract.View {

    private ActivityDashboardBinding binding;

    @Inject
    DashboardContract.Presenter presenter;
    private ProgressDialog progressDialog;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DashboardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        setSupportActionBar(binding.customToolbar);
        binding.ivToolbarImage.setImageResource(R.mipmap.ic_launcher_round);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboardContainer, DashboardFragment.getInstance())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void setToolbarTitle(String type) {
        binding.tvToolbarText.setText(type);
    }

    @Override
    public void showProgress() {
        progressDialog = Commons.showLoadingDialog(this);
    }

    @Override
    public void onLogoutSelected() {
        hideProgress();
        Activity activity = this;
        startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    @Override
    public void showNetworkNotAvailableError(String message) {
        hideProgress();
        Commons.showSnackBar(this, binding.llMainView, message);
    }

    @Override
    public void showError(String message) {
        hideProgress();
        Commons.showSnackBar(this, binding.llMainView, message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.notification_src:
                NotificationActivity.start(this);
                break;
            case R.id.profile_src:
                ProfileActivity.start(this);
                break;

            case R.id.phone_src:
                ContactsActivity.start(this);
                break;

            case R.id.note_src:
                NoteListActivity.start(this);
                break;

            case R.id.logout_src:
                presenter.onLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}

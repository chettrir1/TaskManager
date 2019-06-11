package com.techsales.taskmanager.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ContentStateViewBinding;

import static com.techsales.taskmanager.utils.Commons.checkNotNull;

public class ContentStateView extends FrameLayout {
    private ContentStateViewBinding binding;

    @NonNull
    private View contentView;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public ContentStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.content_state_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setContent(View contentView) {
        this.contentView = contentView;
    }

    public void showProgress() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(VISIBLE);
        this.contentView.setVisibility(GONE);
        this.binding.pbConnectionState.setVisibility(VISIBLE);
        this.binding.ivContentState.setVisibility(GONE);
        this.binding.tvConnectionState.setVisibility(GONE);
    }

    public void showProgress(String message) {
        checkNotNull(contentView, message);
        setVisibility(VISIBLE);
        this.binding.pbConnectionState.setVisibility(VISIBLE);
    }

    public void showError(@StringRes int message) {
        this.binding.tvConnectionState.setText(message);
        errorVisible();
    }

    public void showError(@DrawableRes int image, @StringRes int message) {
        this.binding.tvConnectionState.setText(message);
        binding.ivContentState.setImageDrawable(ContextCompat.getDrawable(binding.ivContentState.getContext(),image));
        errorVisible();
    }

    public void showError(@DrawableRes int image, String message) {
        this.binding.tvConnectionState.setText(message);
        binding.ivContentState.setImageDrawable(ContextCompat.getDrawable(binding.ivContentState.getContext(),image));
        errorVisible();
    }

    public void showError(String message) {
        this.binding.tvConnectionState.setText(message);
        errorVisible();
    }

    public void showError() {
        this.binding.tvConnectionState.setText("");
        errorVisible();
    }

    private void errorVisible() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(VISIBLE);
        this.binding.pbConnectionState.setVisibility(GONE);
        this.binding.ivContentState.setVisibility(VISIBLE);
        this.binding.tvConnectionState.setVisibility(VISIBLE);
        this.contentView.setVisibility(GONE);
    }

    public void showContent() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
    }
}

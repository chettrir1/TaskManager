package com.techsales.taskmanager.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.techsales.taskmanager.data.remote.RemoteRepo;

import javax.annotation.Nullable;

import androidx.databinding.BindingAdapter;

public class DataBindingUtils {

    @BindingAdapter("imagePath")
    public void loadImage(ImageView imageView, @Nullable String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            String imageUrl = RemoteRepo.BASE_IMAGE_URL + imagePath;
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }
}

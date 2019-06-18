package com.techsales.taskmanager.utils;

import android.text.TextUtils;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.techsales.taskmanager.data.remote.RemoteRepo;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataBindingUtils {

    @BindingAdapter("imagePath")
    public static void loadImage(CircleImageView imageView, @Nullable String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            String imageUrl = RemoteRepo.BASE_IMAGE_URL + imagePath;
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }
}

package com.smileflowpig.money.moneyplatfrom.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smileflowpig.money.R;
import com.smileflowpig.money.moneyplatfrom.weight.GlideCircleTransform;
import com.smileflowpig.money.moneyplatfrom.weight.GlideRoundTransform;

import java.io.File;

public class GlideUtil {

    public static void setImageWithNoCache(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void setImageWithCirlce(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void setImageWithCirlce(Context context, String url, ImageView imageView, int placeDrawable) {
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).placeholder(placeDrawable).diskCacheStrategy(DiskCacheStrategy.ALL).error(placeDrawable).into(imageView);
    }

    public static void setImageWithcircleError(Context context, String url, ImageView imageView, int error) {
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).error(error).into(imageView);
    }

    public static void setImageWithNoCache(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void setImageWithNoCache(Context context, String url, ImageView imageView, boolean placeholder) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void setImageViewCrop(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).transform(new GlideRoundTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
    public static void setImageViewCrop(Context context, int url, ImageView imageView) {
        Glide.with(context).load(url).transform(new GlideRoundTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }


    public static void setImageWithCache(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void setImageWithCache(Context context, String url, ImageView imageView, int error_drawable, int place) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(error_drawable).placeholder(place).into(imageView);
    }

    public static void setImageWithCache(Context context, String url, ImageView imageView, int place) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(place).into(imageView);
    }

    public static void setImageWithCacheError(Context context, String url, ImageView imageView, int error) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(error).into(imageView);
    }
}
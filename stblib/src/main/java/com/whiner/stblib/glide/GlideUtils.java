package com.whiner.stblib.glide;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.whiner.stblib.R;
import com.whiner.stblib.net.UrlUtils;

public class GlideUtils {

    public static void loadRoundImg(String img, ImageView imageView, FragmentActivity activity) {
        if (!activity.isDestroyed()) {
            int radius = activity.getResources().getDimensionPixelSize(R.dimen.card_radius);
            Glide.with(activity)
                    .load(UrlUtils.INSTANCE.useUrl(img, "GlideUtils.loadRoundImg"))
                    .placeholder(R.drawable.bg_gray_card)
                    .error(R.drawable.bg_gray_card)
                    .apply(new RequestOptions().transform(new MyRoundedCorners(radius)))
                    .into(imageView);
        }
    }

    public static void loadRoundImg(@DrawableRes int img, ImageView imageView, FragmentActivity activity) {
        if (!activity.isDestroyed()) {
            int radius = activity.getResources().getDimensionPixelSize(R.dimen.card_radius);
            Glide.with(activity)
                    .load(img)
                    .placeholder(R.drawable.bg_gray_card)
                    .error(R.drawable.bg_gray_card)
                    .apply(new RequestOptions().transform(new MyRoundedCorners(radius)))
                    .into(imageView);
        }
    }

}

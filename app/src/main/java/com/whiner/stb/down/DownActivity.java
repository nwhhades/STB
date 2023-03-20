package com.whiner.stb.down;

import android.content.Context;
import android.content.Intent;

import com.whiner.stb.databinding.ActivityDownBinding;
import com.whiner.stblib.base.BaseActivity;

public class DownActivity extends BaseActivity<ActivityDownBinding> {

    public static void start(Context context){
        Intent intent = new Intent(context,DownActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected ActivityDownBinding getBinding() {
        return ActivityDownBinding.inflate(getLayoutInflater());
    }

    @Override
    protected boolean enableBg() {
        return false;
    }

    @Override
    protected boolean enableLoadingView() {
        return false;
    }

    @Override
    protected void init() {

    }

}

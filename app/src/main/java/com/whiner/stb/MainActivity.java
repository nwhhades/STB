package com.whiner.stb;

import android.view.View;

import com.whiner.stb.databinding.ActivityMainBinding;
import com.whiner.stblib.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected ActivityMainBinding getBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected boolean enableBg() {
        return true;
    }

    @Override
    protected boolean enableLoadingView() {
        return true;
    }


    @Override
    protected void preInit() {
        super.preInit();
        setBgUrl("http://static.runoob.com/images/demo/demo3.jpg");
    }

    @Override
    protected void init() {
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView("sssss");
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLoadingView();
            }
        });
    }

}
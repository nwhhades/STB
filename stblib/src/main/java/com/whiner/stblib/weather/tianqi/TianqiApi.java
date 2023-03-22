package com.whiner.stblib.weather.tianqi;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;
import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestCache;
import com.hjq.http.config.IRequestHost;
import com.hjq.http.model.CacheMode;

public class TianqiApi implements IRequestHost, IRequestApi, IRequestCache {

    @HttpIgnore
    private static final String TAG = "TianqiApi";

    @HttpIgnore
    private static final String defaultUrl = "https://www.yiketianqi.com/free/day?appid=41599835&appsecret=3I9UiEZd&unescape=1";


    @NonNull
    @Override
    public String getHost() {
        return getWeatherUrl();
    }

    @NonNull
    @Override
    public String getApi() {
        return "";
    }

    @NonNull
    @Override
    public CacheMode getCacheMode() {
        return CacheMode.USE_CACHE_ONLY;
    }

    @Override
    public long getCacheTime() {
        return 3600000;
    }

    public static String getWeatherUrl() {
        return SPUtils.getInstance().getString(TAG, defaultUrl);
    }

    public static void saveWeatherUrl(String url) {
        SPUtils.getInstance().put(TAG, url);
    }

}

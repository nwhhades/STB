package com.whiner.stb.net;

import androidx.annotation.NonNull;

import com.hjq.http.model.CacheMode;
import com.whiner.stblib.net.BaseApi;

public class SetApi extends BaseApi<SettingsBean> {

    @NonNull
    @Override
    public String getApi() {
        return "api/settings/getSettings";
    }

    @NonNull
    @Override
    public CacheMode getCacheMode() {
        return CacheMode.USE_CACHE_AFTER_FAILURE;
    }

    @Override
    public long getCacheTime() {
        return 1000 * 60 * 60;
    }

}

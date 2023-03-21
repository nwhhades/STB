package com.whiner.stblib.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.http.EasyHttp;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestCache;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.request.GetRequest;
import com.whiner.stblib.base.NetBaseActivity;

import okhttp3.Call;

public abstract class BaseApi<T> implements IRequestApi, IRequestCache {

    private static final String TAG = "BaseApi";

    public void get(@NonNull NetBaseActivity<?> netBaseActivity, @Nullable OnHttpListener<NetResult<T>> listener, @Nullable String tag, long delay) {
        GetRequest request = EasyHttp.get(netBaseActivity);
        request.api(this);
        if (tag != null) {
            request.tag(tag);
        }
        if (delay > 0) {
            request.delay(delay);
        }
        request.request(new HttpCallback<NetResult<T>>(listener) {

            @Override
            public void onStart(Call call) {
                if (!netBaseActivity.isDestroyed()) {
                    netBaseActivity.onStart(call);
                }
            }

            @Override
            public void onEnd(Call call) {
                if (!netBaseActivity.isDestroyed()) {
                    netBaseActivity.onEnd(call);
                }
            }

            @Override
            public void onSucceed(NetResult<T> result, boolean cache) {
                super.onSucceed(result, cache);
                if (cache) {
                    Log.e(TAG, "onSucceed: 使用了缓存");
                }
                Log.d(TAG, "onSucceed: " + result.toString());
            }

            @Override
            public void onFail(Exception e) {
                super.onFail(e);
                Log.e(TAG, "onFail: 请求失败", e);
            }

        });
    }

    public void cancel(@NonNull String tag) {
        EasyHttp.cancel(tag);
    }

    public void cancel(@NonNull NetBaseActivity<?> netBaseActivity) {
        EasyHttp.cancel(netBaseActivity);
    }

}

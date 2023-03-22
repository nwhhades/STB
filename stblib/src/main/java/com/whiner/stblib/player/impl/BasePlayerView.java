package com.whiner.stblib.player.impl;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.whiner.stblib.player.base.IPlayerView;

public abstract class BasePlayerView implements IPlayerView {

    protected boolean mMute = false;
    protected volatile PlayState mPlayState = PlayState.STATE_IDLE;
    protected OnPlayListener mOnPlayListener;
    protected final Handler mHandler = new Handler(Looper.myLooper());
    protected final Runnable mF5ProgressRunnable = new Runnable() {
        @Override
        public void run() {
            f5Progress();
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void addToVideoView(ViewGroup root) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = getView(root.getContext());
        if (view != null) {
            root.addView(view, 0, layoutParams);
        }
        addListener();
    }

    @Override
    public void delToVideoView(ViewGroup root) {
        delListener();
        release();
        View view = getView(root.getContext());
        if (view != null) {
            root.removeView(view);
        }
    }


    @Override
    public void setPlaySate(PlayState playState) {
        mPlayState = playState;
        if (mOnPlayListener != null) {
            mOnPlayListener.onPlayState(mPlayState);
        }
    }

    @Override
    public void setOnPlayListener(OnPlayListener onPlayListener) {
        mOnPlayListener = onPlayListener;
    }

    @Override
    public OnPlayListener getOnPlayListener() {
        return mOnPlayListener;
    }

    @Override
    public void setErrState(Exception e) {
        setPlaySate(PlayState.STATE_ERROR);
        if (mOnPlayListener != null) {
            mOnPlayListener.onPlayErr(e);
        }
    }

    @Override
    public void setCompletion() {
        setPlaySate(PlayState.STATE_PLAYBACK_COMPLETED);
        if (mOnPlayListener != null) {
            mOnPlayListener.onPlayCompleted();
        }
    }

    @Override
    public void startF5Progress() {
        mHandler.post(mF5ProgressRunnable);
    }

    @Override
    public void stopF5Progress() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void f5Progress() {
        if (mOnPlayListener != null && isPlaying()) {
            long time1 = getCurrentPosition();
            long time2 = getDuration();
            //处理直播流的情况
            if (time2 <= 0) {
                time2 = 1;
                time1 = 1;
            }
            float progress = time1 * 100f / time2;
            mOnPlayListener.onProgress((int) progress, time1, time2);
        }
    }

    @Override
    public boolean isMute() {
        return mMute;
    }

    @Override
    public void setMute(boolean mute) {
        mMute = mute;
    }

    @Override
    public void setProgress(int progress) {
        float cur_time = progress / 100f * getDuration();
        seekTo((int) cur_time);
    }

    @Override
    public int getProgress() {
        if (isPlaying()) {
            long time1 = getCurrentPosition();
            long time2 = getDuration();
            if (time2 > 0) {
                float progress = time1 * 100f / time2;
                return (int) progress;
            }
        }
        return 0;
    }

}

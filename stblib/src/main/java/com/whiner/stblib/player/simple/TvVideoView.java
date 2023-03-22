package com.whiner.stblib.player.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;

import com.whiner.stblib.R;
import com.whiner.stblib.player.base.IControllerView;
import com.whiner.stblib.player.base.IPlayerView;
import com.whiner.stblib.player.base.IPlayerViewFactory;
import com.whiner.stblib.player.base.IVideoView;
import com.whiner.stblib.player.base.PlayerConfig;
import com.whiner.stblib.view.TvSpinner;

public class TvVideoView extends FrameLayout implements IVideoView, IPlayerView.OnPlayListener, IControllerView.OnControllerActionListener {

    private static final String TAG = "TvVideoView";

    private volatile String mUrl;

    private TvSpinner mTvSpinner;
    private ProgressBar mProgressBar;
    private IPlayerView mPlayerView;
    private IControllerView mControllerView;

    public TvVideoView(@NonNull Context context) {
        super(context);
        init();
    }

    public TvVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TvVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void init() {
        addLoadingView();
        setClickable(true);
        setFocusable(true);
        setOnClickListener(v -> {
            if (mControllerView != null) {
                mControllerView.toggleShow();
            }
        });
    }

    @Override
    public void addLoadingView() {
        mProgressBar = new ProgressBar(new ContextThemeWrapper(getContext(), R.style.ProgressBar), null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mProgressBar.setId(R.id.pb_loading);
        mProgressBar.setVisibility(INVISIBLE);
        addView(mProgressBar, layoutParams);
    }

    @Override
    public void showLoadingView() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideLoadingView() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void setPlayerView(IPlayerView playerView) {
        if (mPlayerView != null) {
            mPlayerView.setOnPlayListener(null);
            mPlayerView.delToVideoView(this);
        }
        mPlayerView = playerView;
        if (mPlayerView != null) {
            mPlayerView.addToVideoView(this);
            mPlayerView.setOnPlayListener(this);
        }
        //如果之前就有地址的话，继续播放
        if (mUrl != null) {
            play(mUrl, false);
        }
    }

    @Override
    public IPlayerView getPlayerView() {
        return mPlayerView;
    }

    @Override
    public void setControllerView(IControllerView controllerView) {
        if (mControllerView != null) {
            mControllerView.setOnControllerActionListener(null);
            mControllerView.delToVideoView(this);
        }
        mControllerView = controllerView;
        if (mControllerView != null) {
            mControllerView.addToVideoView(this);
            mControllerView.setOnControllerActionListener(this);
        }
    }

    @Override
    public IControllerView getControllerView() {
        return mControllerView;
    }

    @Override
    public void create() {
        //读取配置
        mPlayerView = null;
        IPlayerViewFactory<?> playerViewFactory = PlayerConfig.INSTANCE.getCurPlayer();
        if (playerViewFactory != null) {
            mPlayerView = playerViewFactory.getPlayerView();
        }
        mControllerView = new TvController();

        setPlayerView(mPlayerView);
        setControllerView(mControllerView);
    }

    @Override
    public void setTitle(String title) {
        if (mControllerView != null) {
            mControllerView.setTextToView(R.id.tv_title, title);
        }
    }

    @Override
    public void play(String url, boolean looping) {
        mUrl = url;
        if (mPlayerView != null) {
            mPlayerView.setUrl(url);
            mPlayerView.setLooping(looping);
            mPlayerView.start();
        }
    }

    @Override
    public void resume() {
        if (mPlayerView != null && !mPlayerView.isPlaying()) {
            mPlayerView.resume();
        }
    }

    @Override
    public void pause() {
        if (mPlayerView != null) {
            mPlayerView.pause();
        }
    }

    @Override
    public void destroy() {
        setPlayerView(null);
        setControllerView(null);
    }

    @Override
    public void onPlayErr(Exception e) {
        if (mControllerView != null) {
            mControllerView.setErrUI();
        }
    }

    @Override
    public void onPlayCompleted() {
        if (mControllerView != null) {
            mControllerView.setCompletedUI();
        }
    }

    @Override
    public void onPlayState(IPlayerView.PlayState state) {
        Log.d(TAG, "onPlayState: " + state);
        switch (state) {
            case STATE_PLAYING:
                //处理加载
                hideLoadingView();
                if (mControllerView != null) {
                    mControllerView.setPlayingUI();
                }
                break;
            case STATE_PAUSED:
                if (mControllerView != null) {
                    mControllerView.setPauseUI();
                }
                break;
            case STATE_MUTE:
                if (mControllerView != null) {
                    mControllerView.setMuteUI(true);
                }
                break;
            case STATE_UN_MUTE:
                if (mControllerView != null) {
                    mControllerView.setMuteUI(false);
                }
                break;
            case STATE_BUFFERING:
                showLoadingView();
                break;
            case STATE_BUFFERED:
                hideLoadingView();
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgress(int progress, long time1, long time2) {
        if (mControllerView != null) {
            mControllerView.setProgress(time1, time2);
        }
    }

    @Override
    public void onClickView(View view) {
        int id = view.getId();
        if (id == R.id.btn1) {
            if (mPlayerView != null) {
                if (mPlayerView.isPlaying()) {
                    mPlayerView.pause();
                } else {
                    mPlayerView.resume();
                }
            }
        } else if (id == R.id.btn2) {
            if (mPlayerView != null) {
                mPlayerView.replay();
            }
        } else if (id == R.id.btn3) {
            if (mPlayerView != null) {
                mPlayerView.setMute(!mPlayerView.isMute());
            }
        } else if (id == R.id.btn4) {
            if (mTvSpinner == null) {
                mTvSpinner = new TvSpinner(getContext(), "选择播放器内核", PlayerConfig.INSTANCE.getPlayerList(), PlayerConfig.INSTANCE.getCurPlayerIndex());
                mTvSpinner.setOnSelectItemListener((item, index) -> {
                    PlayerConfig.INSTANCE.select(index);
                    setPlayerView(PlayerConfig.INSTANCE.getCurPlayer().getPlayerView());
                });
            }
            mTvSpinner.show();
        } else {
            Log.d(TAG, "onClickView: 未知按钮");
        }
    }

    @Override
    public void onUserChangeProgress(int progress) {
        if (mPlayerView != null) {
            //强制进度调整不能超过98
            if (progress > 98) {
                progress = 98;
            }
            mPlayerView.setProgress(progress);
        }
    }

    @Override
    public void onShow() {
        Log.d(TAG, "onShow: 控制器显示了");
        if (mPlayerView != null) {
            mPlayerView.startF5Progress();
        }
    }

    @Override
    public void onHide() {
        Log.d(TAG, "onShow: 控制器隐藏了");
        if (mPlayerView != null) {
            mPlayerView.stopF5Progress();
        }
    }

}

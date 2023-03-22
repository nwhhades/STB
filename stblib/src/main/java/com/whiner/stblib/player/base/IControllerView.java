package com.whiner.stblib.player.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface IControllerView {

    interface OnControllerActionListener {

        void onClickView(View view);

        void onUserChangeProgress(int progress);

        void onShow();

        void onHide();

    }

    View getView(Context context);

    void addToVideoView(ViewGroup root);

    void delToVideoView(ViewGroup root);

    void addListener();

    void delListener();

    void setOnControllerActionListener(OnControllerActionListener onControllerActionListener);

    OnControllerActionListener getOnControllerActionListener();

    void setProgress(int progress);

    void setProgress(long time1, long time2);

    void setTextToView(int view_id, String text);

    void setBackgroundResourceToView(int view_id, int res_id);

    void setPlayingUI();

    void setPauseUI();

    void setErrUI();

    void setCompletedUI();

    void setMuteUI(boolean mute);

    void show();

    void showRequestFocus();

    void hide();

    boolean isShow();

    void toggleShow();

    void autoHide();

    void cancelAutoHide();

}

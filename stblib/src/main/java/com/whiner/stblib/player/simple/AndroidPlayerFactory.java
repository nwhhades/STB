package com.whiner.stblib.player.simple;

import com.whiner.stblib.player.base.IPlayerViewFactory;

public class AndroidPlayerFactory implements IPlayerViewFactory<AndroidPlayer> {
    @Override
    public String getFactoryName() {
        return "原生播放器";
    }

    @Override
    public AndroidPlayer getPlayerView() {
        return new AndroidPlayer();
    }

}

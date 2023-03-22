package com.whiner.stb.ijk;

import com.whiner.stblib.player.base.IPlayerViewFactory;

public class IjkPlayerFactory implements IPlayerViewFactory<IjkPlayer> {

    @Override
    public String getFactoryName() {
        return "IJK播放器";
    }

    @Override
    public IjkPlayer getPlayerView() {
        return new IjkPlayer();
    }

}

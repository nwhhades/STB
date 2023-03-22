package com.whiner.stblib.player.base;

import java.io.Serializable;

public interface IPlayerViewFactory<T extends IPlayerView> extends Serializable {

    String getFactoryName();

    T getPlayerView();

}

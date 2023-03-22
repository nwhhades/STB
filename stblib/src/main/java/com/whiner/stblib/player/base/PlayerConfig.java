package com.whiner.stblib.player.base;

import com.blankj.utilcode.util.CacheDiskUtils;
import com.whiner.stblib.player.simple.AndroidPlayerFactory;

import java.util.ArrayList;
import java.util.List;

public enum PlayerConfig {
    INSTANCE;

    private static final String TAG = "PlayerConfig";
    private final List<IPlayerViewFactory<?>> list = new ArrayList<>();
    private IPlayerViewFactory<?> mFactory = null;

    public void init() {
        list.add(new AndroidPlayerFactory());
        mFactory = (IPlayerViewFactory<?>) CacheDiskUtils.getInstance().getSerializable(TAG, list.get(0));
    }

    public void add(IPlayerViewFactory<?> factory) {
        list.add(factory);
    }

    public void select(int index) {
        if (index < list.size()) {
            mFactory = list.get(index);
            CacheDiskUtils.getInstance().put(TAG, mFactory);
        }
    }

    public IPlayerViewFactory<?> getCurPlayer() {
        return mFactory;
    }

    public int getCurPlayerIndex() {
        if (mFactory != null) {
            return list.indexOf(mFactory);
        }
        return 0;
    }

    public List<String> getPlayerList() {
        List<String> stringList = new ArrayList<>();
        for (IPlayerViewFactory<?> item : list) {
            stringList.add(item.getFactoryName());
        }
        return stringList;
    }

}

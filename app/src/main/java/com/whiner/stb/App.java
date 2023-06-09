package com.whiner.stb;

import androidx.annotation.NonNull;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.whiner.stb.ijk.IjkPlayerFactory;
import com.whiner.stblib.base.BaseApplication;
import com.whiner.stblib.net.UrlUtils;
import com.whiner.stblib.player.base.PlayerConfig;

import java.util.List;

public class App extends BaseApplication {

    @Override
    protected void init() {
        PlayerConfig.INSTANCE.add(new IjkPlayerFactory());
        PlayerConfig.INSTANCE.init();
    }

    @Override
    protected UrlUtils.Impl initUrlUtilsImpl() {
        return (url, tag) -> {
            switch (tag) {
                case "GlideUtils.loadRoundImg":
                case "BaseActivity.createBg":
                    if (url != null) {
                        url = url.replace("https://", "http://");
                    }
                    break;
                case "BaseActivity.creat2eBg":
                    if (url != null) {
                        url = "http:// 22+ " + url;
                    }
                    break;
                default:
                    break;
            }
            return url;
        };
    }

}

package com.whiner.stb;

import com.whiner.stblib.base.BaseApplication;
import com.whiner.stblib.net.UrlUtils;

public class App extends BaseApplication {

    @Override
    protected void init() {

    }

    @Override
    protected void initPermissions() {

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

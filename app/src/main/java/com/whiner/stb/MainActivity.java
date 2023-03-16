package com.whiner.stb;

import android.util.Log;
import android.view.View;

import com.whiner.stb.databinding.ActivityMainBinding;
import com.whiner.stblib.base.BaseActivity;
import com.whiner.stblib.net.DownloadNetFileUtils;
import com.whiner.stblib.net.NetFile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private static final String TAG = "MainActivity";

    @Override
    protected ActivityMainBinding getBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected boolean enableBg() {
        return true;
    }

    @Override
    protected boolean enableLoadingView() {
        return true;
    }


    @Override
    protected void preInit() {
        super.preInit();
        setBgUrl("http://static.runoob.com/images/demo/demo3.jpg");
    }

    @Override
    protected void init() {
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showLoadingView("sssss");
                initDown();
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hideLoadingView();
                stopDown();
            }
        });


    }

    private String tag;
    private final DownloadNetFileUtils.OnListener onDownListener = new DownloadNetFileUtils.OnListener() {
        private volatile boolean b = false;

        @Override
        public void onStart(List<NetFile> list) {
            Log.d(TAG, "onStart: " + list);
        }

        @Override
        public void onEnd(List<NetFile> list) {
            Log.d(TAG, "onEnd: " + list);
        }

        @Override
        public void onProgress(int progress, int taskIndex) {
            Log.d(TAG, "onProgress: " + progress + " - " + taskIndex);
        }

        @Override
        public boolean isCanceled() {
            return b;
        }

        @Override
        public void setCanceled(boolean canceled) {
            b = canceled;
        }
    };

    private void initDown() {
        NetFile netFile1 = new NetFile();
        netFile1.setIndex(0);
        netFile1.setName("MT2.13.2.apk");
        netFile1.setUrl("https://node13.cdn.dfyun.com.cn:8888/f/N4Igzg9grgTgxgUwEIEMwJALhACwC54AOYmA9KQCYA2ATAHQC2e9cAdiADTjTwIByKBhmxManEADMAllQQAFFHhxYQpALIAVegEYAzHXopCAaxABfIA=/MT2.13.2.apk?_ex=1680278399&_si=f388802792");
        netFile1.setMd5("84331c04b631b2c19c818fbada73a859");

        NetFile netFile2 = new NetFile();
        netFile2.setIndex(1);
        netFile2.setName("SteamSetup.exe");
        netFile2.setUrl("https://cdn.akamai.steamstatic.com/client/installer/SteamSetup.exe");
        netFile2.setMd5("70f3bc193dfa56b78f3e6e4f800f701f");

        NetFile netFile3 = new NetFile();
        netFile3.setIndex(2);
        netFile3.setName("MD5.bat");
        netFile3.setUrl("https://s31.lanzoug.com:446/0316110094233633bb/2022/12/20/a05287d1cb7240f6378f1524c94948e6.bat.txt?st=tna42sjcpRc2g5CZXz3EzA&e=1678940329&b=VEtZHQA1UHsEM15pUHQ_c&fi=94233633&pid=182-109-138-78&up=2&mp=0&co=1");
        netFile3.setMd5("ac61c4e26c90b918d945377910865146");

        List<NetFile> list = new ArrayList<>();
        list.add(netFile1);
        list.add(netFile2);
        list.add(netFile3);

        tag = DownloadNetFileUtils.downFiles(this, list, list.get(0), onDownListener);
    }

    private void stopDown() {
        DownloadNetFileUtils.cancel(tag, onDownListener);
    }


}
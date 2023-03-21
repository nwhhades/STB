package com.whiner.stblib.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnDownloadListener;
import com.hjq.http.model.HttpMethod;

import java.io.File;
import java.util.List;

/**
 * 单线程下载文件
 */
public class DownloadNetFileUtils {

    private static final String TAG = "DownloadUtils";

    public interface OnListener {

        void onStart(List<NetFile> list);

        void onEnd(List<NetFile> list);

        void onProgress(int progress, int taskIndex);

        boolean isCanceled();

        void setCanceled(boolean canceled);

    }

    public static String downFiles(@NonNull LifecycleOwner lifecycleOwner, @NonNull List<NetFile> list, @NonNull NetFile netFile, @NonNull OnListener onListener) {
        String tag = onListener.toString();
        int list_size = list.size();
        int index = netFile.getIndex();
        onListener.setCanceled(false);
        EasyHttp.download(lifecycleOwner)
                .tag(tag)
                .method(HttpMethod.GET)
                .file(createFile(netFile))
                .url(netFile.getUrl())
                .md5(netFile.getMd5())
                .listener(new OnDownloadListener() {
                    @Override
                    public void onStart(File file) {
                        Log.d(TAG, "onStart: " + file);
                        if (index == 0) {
                            onListener.onStart(list);
                        }
                    }

                    @Override
                    public void onProgress(File file, int progress) {
                        Log.d(TAG, "onProgress: " + progress);
                        onListener.onProgress(progress, index);
                    }

                    @Override
                    public void onComplete(File file) {
                        Log.d(TAG, "onComplete: " + file);
                        netFile.setDown(true);
                        netFile.setFilePath(file.getPath());
                    }

                    @Override
                    public void onError(File file, Exception e) {
                        Log.e(TAG, "onError: " + file, e);
                        netFile.setDown(false);
                        netFile.setFilePath(null);
                    }

                    @Override
                    public void onEnd(File file) {
                        Log.d(TAG, "onEnd: " + file);
                        //判断是否是最后一个任务
                        if (index == list_size - 1 || onListener.isCanceled()) {
                            onListener.onEnd(list);
                        } else {
                            downFiles(lifecycleOwner, list, list.get(netFile.getIndex() + 1), onListener);
                        }
                    }
                }).start();
        return tag;
    }

    public static void cancel(@NonNull OnListener onListener) {
        onListener.setCanceled(true);
        String tag = onListener.toString();
        EasyHttp.cancel(tag);
    }

    private static File createFile(@NonNull NetFile netFile) {
        File file;
        String file_path = PathUtils.getExternalAppDataPath() + "/" + netFile.getName();
        Log.d(TAG, "createFile: " + file_path);
        file = FileUtils.getFileByPath(file_path);
        return file;
    }

}

package com.whiner.stblib.net;

import androidx.annotation.NonNull;

public class NetFile {

    private String name;
    private String url;
    private String md5;
    private int index;
    private boolean isDown;
    private String filePath;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @NonNull
    @Override
    public String toString() {
        return "NetFile{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", index=" + index +
                ", isDown=" + isDown +
                ", filePath='" + filePath + '\'' +
                '}';
    }

}

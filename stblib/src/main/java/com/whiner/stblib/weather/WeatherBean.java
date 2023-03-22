package com.whiner.stblib.weather;

import androidx.annotation.NonNull;

public class WeatherBean {

    private String city;//城市
    private String wea;//天气情况
    private int wea_img;//天气图标
    private String tem;//实时温度
    private String tem_day;//白天温度（高温）
    private String tem_night;//夜间温度（低温）

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public int getWea_img() {
        return wea_img;
    }

    public void setWea_img(int wea_img) {
        this.wea_img = wea_img;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem_day() {
        return tem_day;
    }

    public void setTem_day(String tem_day) {
        this.tem_day = tem_day;
    }

    public String getTem_night() {
        return tem_night;
    }

    public void setTem_night(String tem_night) {
        this.tem_night = tem_night;
    }

    @NonNull
    @Override
    public String toString() {
        return "WeatherBean{" +
                "city='" + city + '\'' +
                ", wea='" + wea + '\'' +
                ", wea_img=" + wea_img +
                ", tem='" + tem + '\'' +
                ", tem_day='" + tem_day + '\'' +
                ", tem_night='" + tem_night + '\'' +
                '}';
    }

}

package com.whiner.stblib.weather;

public interface WeatherFactory {

    void getData(OnWeatherListener onWeatherListener);

    void stopGet();

}

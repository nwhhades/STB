package com.whiner.stblib.weather.tianqi;

import com.hjq.http.EasyHttp;
import com.hjq.http.lifecycle.ApplicationLifecycle;
import com.hjq.http.listener.HttpCallback;
import com.whiner.stblib.R;
import com.whiner.stblib.weather.OnWeatherListener;
import com.whiner.stblib.weather.WeatherBean;
import com.whiner.stblib.weather.WeatherFactory;

public class TianqiFactory implements WeatherFactory {

    private static final String TAG = "TianqiFactory";

    private final TianqiApi tianqiApi = new TianqiApi();
    private OnWeatherListener onWeatherListener;

    @Override
    public void getData(OnWeatherListener listener) {
        if (listener == null) {
            return;
        }
        onWeatherListener = listener;
        EasyHttp.get(ApplicationLifecycle.getInstance())
                .api(tianqiApi)
                .tag(TAG)
                .request(new HttpCallback<TianqiBean>(null) {
                    @Override
                    public void onSucceed(TianqiBean result) {
                        if (onWeatherListener != null) {
                            if (result != null) {
                                WeatherBean weatherBean = new WeatherBean();
                                weatherBean.setCity(result.getCity());
                                weatherBean.setWea(result.getWea());
                                weatherBean.setWea_img(getResIDByStr(result.getWea_img()));
                                weatherBean.setTem(result.getTem());
                                weatherBean.setTem_day(result.getTem_day());
                                weatherBean.setTem_night(result.getTem_night());
                                onWeatherListener.onWeather(weatherBean);
                            }
                        }
                    }

                    @Override
                    public void onFail(Exception e) {
                        if (onWeatherListener != null) {
                            onWeatherListener.onWeather(null);
                        }
                    }
                });
    }

    @Override
    public void stopGet() {
        onWeatherListener = null;
        EasyHttp.cancel(TAG);
    }

    private static int getResIDByStr(String str) {
        int icon;
        switch (str) {
            case "xue":
                icon = R.drawable.ic_weather_xue;
                break;
            case "lei":
                icon = R.drawable.ic_weather_lei;
                break;
            case "shachen":
                icon = R.drawable.ic_weather_shachen;
                break;
            case "wu":
                icon = R.drawable.ic_weather_wu;
                break;
            case "bingbao":
                icon = R.drawable.ic_weather_bingbao;
                break;
            case "yun":
                icon = R.drawable.ic_weather_yun;
                break;
            case "yu":
                icon = R.drawable.ic_weather_yu;
                break;
            case "yin":
                icon = R.drawable.ic_weather_yin;
                break;
            case "qing":
                icon = R.drawable.ic_weather_qing;
                break;
            default:
                icon = R.drawable.ic_weather_err;
                break;
        }
        return icon;
    }

}

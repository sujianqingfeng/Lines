package com.sujian.lines.ui.home;

/**
 * Created by sujian on 2016/9/8.
 * Mail:121116111@qq.com
 */
public class HomeModel implements HomeContract.Model {
    @Override
    public String[] getTabs() {
        return new String[]{"最新","国内","国际","军事","财经","互联网","房产","汽车","体育","娱乐","游戏"};
    }
}

package mirror.fortrun.cn.mvpdemo.view.common;

import android.app.Activity;
import android.os.CountDownTimer;

/**
 * 所有mvpView的父接口
 * 里面写的方法基本上每个activity都要用的
 */

public interface BaseMvpView {
    Activity getActivity();

    void toTime(int millisUntil, CountDownTimer timer);

    //loading隐藏
    void hideLoading();

    //loading显示
    void showLoading();

}

package mirror.fortrun.cn.mvpdemo.view.common;

import android.os.CountDownTimer;

/**
 * 判断view是否和presenter建立连接
 */

public class BaseMvpPresenter<V extends BaseMvpView> implements Presenter<V> {
    private V mvpView;
    private CountDownTimer timer;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    public void checkViewAttach(int count) {
        if (!isAttachView()) {
            return;
        }
        if (count != 0) {
            countdown(count);
        }
    }

    /**
     * 判断 view是否为空
     *
     * @return
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    /**
     * 返回目标view
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }

    /**
     * 倒计时
     * 每个页面都有(所以放到基类当中)
     * @param count 时长
     */
    public void countdown(int count) {
        timer = new CountDownTimer(count, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mvpView.toTime((int) (millisUntilFinished / 1000), timer);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    /**
     * 自定义异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 " +
                    "attachView(MvpView) 方法与View建立连接");
        }
    }
}

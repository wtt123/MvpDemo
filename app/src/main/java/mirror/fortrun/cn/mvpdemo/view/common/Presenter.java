package mirror.fortrun.cn.mvpdemo.view.common;

/**
 * 指定绑定的View必须继承自BaseMvpView
 * 接收BaseMvpView的子类
 */

public interface Presenter<V extends BaseMvpView> {
    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
    void attachView(V mvpView);
    /**
     * presenter与view解绑
     */
    void detachView();

}

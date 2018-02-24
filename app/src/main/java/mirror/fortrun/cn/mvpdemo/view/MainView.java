package mirror.fortrun.cn.mvpdemo.view;

import mirror.fortrun.cn.mvpdemo.view.common.BaseMvpView;

/**
 * Created by wangting on 2018/2/24.
 * MainActivity对于要实现的接口
 */

public interface MainView extends BaseMvpView{
    void toMainActivity(Object data);
}

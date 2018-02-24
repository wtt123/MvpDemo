package mirror.fortrun.cn.mvpdemo.model;

/**
 * Created by wangting on 2018/2/24.
 * 网络请求回调接口
 */

public interface OnBaseListener {
    //请求成功
    void onSuccess(String errcode, String errmsg, Object data);

    //请求失败
    void onFailed(Object data);
}

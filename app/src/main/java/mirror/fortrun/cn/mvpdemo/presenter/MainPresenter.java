package mirror.fortrun.cn.mvpdemo.presenter;

import android.os.Handler;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mirror.fortrun.cn.mvpdemo.model.BaseModel;
import mirror.fortrun.cn.mvpdemo.model.OnBaseListener;
import mirror.fortrun.cn.mvpdemo.model.entity.JSONResponse;
import mirror.fortrun.cn.mvpdemo.model.impl.BaseModelImpl;
import mirror.fortrun.cn.mvpdemo.view.MainView;
import mirror.fortrun.cn.mvpdemo.view.common.BaseMvpPresenter;
import mirror.fortrun.cn.mvpdemo.view.common.IBasePresenter;

/**
 * Created by wangting on 2018/2/24.
 * MainActivity逻辑处理层
 */

public class MainPresenter extends BaseMvpPresenter
        <MainView> implements IBasePresenter {
    private BaseModel baseBiz;
    private Handler mHandler = new Handler();

    public MainPresenter() {
        this.baseBiz = new BaseModelImpl();
    }

    @Override
    public void init(int count) {
        //检查是否绑定
        checkViewAttach(count);
    }

    public void RequestData() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("device_id", "22222");
        baseBiz.post("https://qa.fortrun.cn/libra",
                params, new TypeToken<JSONResponse<String>>() {
                }.getType(), new OnBaseListener() {
                    @Override
                    public void onSuccess(final String errcode,
                                          final String errmsg, final Object data) {
                        //需要在UI线程执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                getMvpView().hideLoading();
                                if (errcode.equals("0") && data != null) {
                                    getMvpView().toMainActivity(data);
                                    return;
                                }
                            }
                        });

                    }

                    @Override
                    public void onFailed(Object data) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                getMvpView().hideLoading();
                                getMvpView().toMainActivity(null);
                            }
                        });
                    }
                });

    }
}

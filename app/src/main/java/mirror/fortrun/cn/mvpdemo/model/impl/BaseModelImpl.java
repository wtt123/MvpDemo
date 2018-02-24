package mirror.fortrun.cn.mvpdemo.model.impl;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.lang.reflect.Type;

import mirror.fortrun.cn.mvpdemo.model.BaseModel;
import mirror.fortrun.cn.mvpdemo.model.OnBaseListener;
import mirror.fortrun.cn.mvpdemo.model.entity.JSONResponse;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by wangting on 2018/2/24.
 * 网络请求封装
 */

public class BaseModelImpl implements BaseModel{
    private static Gson gson = new Gson();
    @Override
    public void post(final String url, final Object requestBody,
                     final Type typeClass, final OnBaseListener
                                 baseListener) {
        new Thread(){
            @Override
            public void run() {
                Response response = null;
                try {
                    response = OkHttpUtils
                            .postString()
                            .url(url)
                            .content(gson.toJson(requestBody).toString())
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (response == null) {
                    baseListener.onFailed(null);
                    return;
                }

                if (response.code() != 200) {
                    baseListener.onFailed(null);
                    return;
                }

                if (typeClass == null) {
                    baseListener.onFailed(null);
                    return;
                }
                    String responseBody;
                    try {
                        responseBody = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException("系统异常");
                    }
                    JSONResponse jsonResponse = gson.fromJson(responseBody, typeClass);
                    baseListener.onSuccess(jsonResponse.getErrcode(), jsonResponse.getErrmsg(),
                            jsonResponse.getData());
                try {
                    baseListener.onSuccess("", "", response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

package mirror.fortrun.cn.mvpdemo.model;

import java.lang.reflect.Type;

/**
 * Created by wangting on 2018/2/24.
 * 网络请求实现接口
 */

public interface BaseModel {
    /**
     * @param url 接口地址
     * @param requestBody 请求参数
     * @param typeClass 解析的具体类(也就是通用JSONResponse里的那个T)
     * @param baseListener
     */
    void post(String url, Object requestBody, Type typeClass,OnBaseListener baseListener);
}

package mirror.fortrun.cn.mvpdemo.model.entity;

import java.io.Serializable;

/**
 * Created by wangting on 2018/2/24.
 * 后台返回的基本数据格式(解析bean类基本格式)
 * T是指具体的bean类
 * 这个类根据你自己后台的数据格式而定
 */

public class JSONResponse <T> implements Serializable {
    private String errcode;
    private String errmsg;
    private T data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.torch.admin.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class R<T> {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public R() {
    }

    public static <T> R<T> ok(){
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }

    public static <T> R<T> error() {
        R<T> r = new R<>();
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }

    public R<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public R<T> data(String key, Object value) {
        this.setData(key,value);
        return this;
    }

    private void setData(String key, Object value) {
    }


    public  R<T> data(T data) {
        this.setData(data);
        return this;
    }

}

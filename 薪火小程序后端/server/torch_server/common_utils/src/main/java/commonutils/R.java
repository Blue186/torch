package commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
//    private Map<String, Object> data = new HashMap<String, Object>();
    private T data;

    public R() {
    }

    public static <T> R<T> ok(){
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static <T> R<T> error() {
        R<T> r = new R<>();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
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

    public R<T> code(Integer code) {
        this.setCode(code);
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

    //-----------------------------------------------------------------------
//        private R() {
//    }
//
//    public static R ok() {
//        R r = new R();
//        r.setSuccess(true);
//        r.setCode(ResultCode.SUCCESS);
//        r.setMessage("成功");
//        return r;
//    }
//
//    public static R error() {
//        R r = new R();
//        r.setSuccess(false);
//        r.setCode(ResultCode.ERROR);
//        r.setMessage("失败");
//        return r;
//    }
//
//    public R success(Boolean success) {
//        this.setSuccess(success);
//        return this;
//    }
//
//    public R message(String message) {
//        this.setMessage(message);
//        return this;
//    }
//
//    public R code(Integer code) {
//        this.setCode(code);
//        return this;
//    }
//
//    public R data(String key, Object value) {
//        this.data.put(key, value);
//        return this;
//    }
//
//    public R data(Map<String, Object> map) {
//        this.setData(map);
//        return this;
//    }
//    public R data(R obj) {
//        this.setData(obj);
//        return this;
//    }

}

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

    public void setData(T data) {
        this.data = data;
    }

    public  R<T> data(T data) {
        this.setData(data);
        return this;
    }

    public R<T> setReLoginData() {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("code", -100);
        this.data((T) resultMap);
        return this;
    }

    public R<T> setErrorCode(Integer errorCode) {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("errorCode", errorCode);
        this.data((T) resultMap);
        return this;
    }
}

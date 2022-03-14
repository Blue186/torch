package com.torch.app.util.commonutils;


public interface ResultCode {
    Integer SUCCESS = 2000;

    Integer ERROR = 3000;

    Integer notRegister = 100;//未注册错误码

    Integer fullPeople = 101;//报名人数已满错误码

    Integer noPeople = 102;//报名人数少于0错误码

    Integer timeOut = 103;//超时错误码

    Integer notSatisfySign = 104;//不满足志愿报名条件，时间冲突

    Integer wrongMsg = 105;//代表错误的请求，布隆过滤器中没有。
}

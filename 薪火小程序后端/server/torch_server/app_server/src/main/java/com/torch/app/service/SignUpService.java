package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.vo.SignUpCon.Sign;
import com.torch.app.entity.vo.SignUpCon.SignUpInfo;

import java.util.List;

public interface SignUpService extends IService<SignUp> {
    List<?> getSignUpInfo(Integer uid,Integer done);
    Boolean satisfySign(Integer uid, Sign sign);
}

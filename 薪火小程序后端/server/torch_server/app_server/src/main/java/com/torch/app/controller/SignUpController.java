package com.torch.app.controller;

import com.torch.app.entity.SignUp;
import com.torch.app.service.SignUpService;
import commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
    @Resource
    private SignUpService signUpService;

    /**
     * 添加用户报名信息
     * @param signUp 报名信息
     * @return 状态
     */
    @ApiOperation("添加用户报名信息")
    @PostMapping()
    public R sign(@ApiParam(name = "signUp", value = "用户的报名信息", required = true) @RequestBody SignUp signUp){
        int res = signUpService.getBaseMapper().insert(signUp);
        if (res==1){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

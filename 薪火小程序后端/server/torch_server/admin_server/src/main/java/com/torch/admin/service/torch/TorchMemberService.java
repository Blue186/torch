package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchMember;

public interface TorchMemberService extends IService<TorchMember> {

    Integer getIdByAccountCodeAndPassword(String accountCode, String password);

}

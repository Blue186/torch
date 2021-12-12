package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchMember;
import com.torch.admin.entity.torch.vo.TorchUserRegister;

import java.util.List;

public interface TorchMemberService extends IService<TorchMember> {

    Integer getIdByAccountCodeAndPassword(String accountCode, String password);

    Integer addTorchMember(TorchUserRegister register, String identify);

    List<TorchMember> getAllTorchMember(Integer page, Integer limit);

    TorchMember selectById(Integer id);

    Integer setPowerId(Integer uid, Integer powerId);

}
